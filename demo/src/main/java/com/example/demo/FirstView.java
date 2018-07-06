package com.example.demo;

import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;


@SpringView
public class FirstView extends VerticalLayout implements View {

    @Autowired
    private AuthorService service;

    private Author author;
    private Binder<Author> binder = new Binder<>(Author.class);

    private Grid<Author> grid = new Grid(Author.class);

    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private TextField patronymic = new TextField("Patronymic");

    private TextField filterText = new TextField("Filter by name");

    private Button save = new Button("Save", e -> saveCustomer());

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    @PostConstruct
    void init() {

        updateGrid();

        grid.addSelectionListener(e -> updateForm());
        grid.setColumns("firstName", "lastName","patronymic");

        binder.bindInstanceFields(this);

        addComponents(grid, firstName, lastName, patronymic, save);
    }

    private void updateGrid() {
        List<Author> authors = service.findAll();
        grid.setItems(authors);
        setFormVisible(false);
    }

    private void updateForm() {
        if (grid.asSingleSelect().isEmpty()) {
            setFormVisible(false);
        } else {
            author = grid.asSingleSelect().getValue();
            binder.setBean(author);
            setFormVisible(true);
        }
    }

    private void setFormVisible(boolean visible) {
        firstName.setVisible(visible);
        lastName.setVisible(visible);
        patronymic.setVisible(visible);
        save.setVisible(visible);
    }

    private void saveCustomer() {
        service.update(author);
        updateGrid();
    }
}
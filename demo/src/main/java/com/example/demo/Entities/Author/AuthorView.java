package com.example.demo.Entities.Author;

import com.example.demo.Entities.Author.Author;
import com.example.demo.Entities.Author.AuthorService;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;


@SpringView
public class AuthorView extends VerticalLayout implements View {

    private boolean addOrUpdateFlag = false;

    @Autowired
    private AuthorService service;

    private Author author;
    private Binder<Author> binder = new Binder<>(Author.class);

    private Grid<Author> grid = new Grid(Author.class);

    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private TextField patronymic = new TextField("Patronymic");

    private Button add = new Button("Add", e -> insertForm());

    private Button save = new Button("OK", e -> saveCustomer());

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    @PostConstruct
    void init() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        updateGrid();

        grid.addSelectionListener(e -> updateForm());
        grid.setColumns("firstName", "lastName","patronymic");

        binder.bindInstanceFields(this);

        horizontalLayout.addComponents(grid,add);

        addComponents(horizontalLayout, firstName, lastName, patronymic, save);
    }

    private void updateGrid() {
        List<Author> authors = service.findAll();
        grid.setItems(authors);
        setFormVisible(false);
    }

    private void updateForm() {
        addOrUpdateFlag = true;

        if (grid.asSingleSelect().isEmpty()) {
            setFormVisible(false);
        } else {
            author = grid.asSingleSelect().getValue();
            binder.setBean(author);
            setFormVisible(true);
        }
    }

    private void insertForm() {
        addOrUpdateFlag = false;

        firstName.clear();
        lastName.clear();
        patronymic.clear();

        if(grid.getSelectedItems()!=null){
            grid.deselectAll();
        }
        setFormVisible(true);

        author = new Author(firstName.getValue(), lastName.getValue(), patronymic.getValue());
        binder.setBean(author);
    }

    private void setFormVisible(boolean visible) {
        firstName.setVisible(visible);
        lastName.setVisible(visible);
        patronymic.setVisible(visible);
        save.setVisible(visible);
    }

    private void saveCustomer() {
        if(addOrUpdateFlag){
            service.update(author);
        }
        else {
            service.insert(author);
        }
        updateGrid();
    }
}
package com.example.demo;

import com.vaadin.data.Binder;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringUI
public class VaadinUI extends UI {

    @Autowired
    private AuthorService service;

    private Author author;
    private Binder<Author> binder = new Binder<>(Author.class);

    private Grid<Author> grid = new Grid(Author.class);

    private TextField firstName = new TextField("Имя");
    private TextField lastName = new TextField("Фамилия");
    private TextField patronymic = new TextField("Отчество");

    private Button save = new Button("Сохранить", e -> saveCustomer());

    @Override
    protected void init(VaadinRequest request) {
        updateGrid();
        grid.setColumns("firstName", "lastName","patronymic");
        grid.addSelectionListener(e -> updateForm());

        binder.bindInstanceFields(this);

        VerticalLayout layout = new VerticalLayout(grid, firstName, lastName, patronymic, save);
        setContent(layout);
    }

    private void updateGrid() {
        List<Author> customers = service.findAll();
        grid.setItems(customers);
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
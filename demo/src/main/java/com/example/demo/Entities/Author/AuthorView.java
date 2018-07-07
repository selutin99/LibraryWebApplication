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

    private TextField firstName = new TextField("Имя");
    private TextField lastName = new TextField("Фамилия");
    private TextField patronymic = new TextField("Отчество");

    private Button add = new Button("Добавить", e -> insertForm());
    private Button delete = new Button("Удалить", e-> deleteForm());

    private Button save = new Button("OK", e -> saveCustomer());
    private Button cancel = new Button("Отменить", e-> setFormVisible(false));

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    @PostConstruct
    void init() {
        HorizontalLayout hl1 = new HorizontalLayout();
        HorizontalLayout hl2 = new HorizontalLayout();

        VerticalLayout vl = new VerticalLayout();

        delete.setEnabled(false);

        updateGrid();

        grid.addSelectionListener(e -> updateForm());
        grid.setColumns("firstName", "lastName", "patronymic");

        binder.bindInstanceFields(this);

        vl.addComponents(add,delete);
        hl1.addComponents(grid,vl);

        hl2.addComponents(save,cancel);

        addComponents(hl1, firstName, lastName, patronymic, hl2);
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
            delete.setEnabled(true);
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

        if(!grid.asSingleSelect().isEmpty()){
            grid.select(null);
        }
        setFormVisible(true);

        author = new Author(firstName.getValue(), lastName.getValue(), patronymic.getValue());
        binder.setBean(author);
    }

    private void deleteForm() {
        if (grid.asSingleSelect().isEmpty()) {
            delete.setEnabled(false);
        }
        else{
            author = grid.asSingleSelect().getValue();
            binder.setBean(author);
            service.delete(author);
        }
        updateGrid();
    }

    private void setFormVisible(boolean visible) {
        if(!visible){
            grid.deselectAll();
            delete.setEnabled(false);
        }
        firstName.setVisible(visible);
        lastName.setVisible(visible);
        patronymic.setVisible(visible);
        save.setVisible(visible);
        cancel.setVisible(visible);
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
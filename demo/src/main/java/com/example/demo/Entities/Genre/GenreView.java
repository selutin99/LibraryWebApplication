package com.example.demo.Entities.Genre;

import com.example.demo.Entities.Author.Author;
import com.example.demo.Entities.Author.AuthorService;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;


@SpringView
public class GenreView extends VerticalLayout implements View {

    private boolean addOrUpdateFlag = false;

    @Autowired
    private GenreService service;

    private Genre genre;
    private Binder<Genre> binder = new Binder<>(Genre.class);

    private Grid<Genre> grid = new Grid(Genre.class);

    private TextField title = new TextField("Название");

    private Button add = new Button("Добавить", e -> insertForm());
    private Button delete = new Button("Удалить", e-> deleteForm());

    private Button save = new Button("OK", e -> saveGenre());
    private Button cancel = new Button("Отменить", e-> setFormVisible(false));

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    @PostConstruct
    void init() {
        HorizontalLayout hl1 = new HorizontalLayout();
        HorizontalLayout hl2 = new HorizontalLayout();
        VerticalLayout vl = new VerticalLayout();

        updateGrid();

        delete.setEnabled(false);
        grid.addSelectionListener(e -> updateForm());
        grid.setColumns("title");

        binder.bindInstanceFields(this);

        vl.addComponents(add,delete);
        hl1.addComponents(grid,vl);
        hl2.addComponents(save,cancel);

        addComponents(hl1, title, hl2);
    }

    private void updateGrid() {
        List<Genre> genres = service.findAll();
        grid.setItems(genres);
        setFormVisible(false);
    }

    private void updateForm() {
        addOrUpdateFlag = true;

        if (grid.asSingleSelect().isEmpty()) {
            setFormVisible(false);
        } else {
            delete.setEnabled(true);
            genre = grid.asSingleSelect().getValue();
            binder.setBean(genre);
            setFormVisible(true);
        }
    }

    private void insertForm() {
        addOrUpdateFlag = false;

        title.clear();

        if(!grid.asSingleSelect().isEmpty()){
            try {
                grid.select(null);
            }
            catch(NullPointerException e){

            }
        }
        setFormVisible(true);

        genre = new Genre(title.getValue());
        binder.setBean(genre);
    }

    private void deleteForm() {
        if (grid.asSingleSelect().isEmpty()) {
            delete.setEnabled(false);
        }
        else{
            genre = grid.asSingleSelect().getValue();
            binder.setBean(genre);
            try {
                service.delete(genre);
            }
            catch (Exception e){
                Notification.show("Удаление невозможно! Запись используется.");
            }
        }
        updateGrid();
    }

    private void setFormVisible(boolean visible) {
        if(!visible){
            if(!grid.asSingleSelect().isEmpty()){
                try {
                    grid.select(null);
                }
                catch(NullPointerException e){

                }
            }
            delete.setEnabled(false);
        }
        title.setVisible(visible);
        save.setVisible(visible);
        cancel.setVisible(visible);
    }

    private void saveGenre() {
        if(title.getValue().equals("")){
            Notification.show("Введите не пустое значение!");
            return;
        }
        if(addOrUpdateFlag){
            service.update(genre);
        }
        else {
            service.insert(genre);
        }
        updateGrid();
    }
}

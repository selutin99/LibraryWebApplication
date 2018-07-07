package com.example.demo.Entities.Book;

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
public class BookView extends VerticalLayout implements View {

    private boolean addOrUpdateFlag = false;

    @Autowired
    private BookService service;

    private Book book;
    private Binder<Book> binder = new Binder<>(Book.class);

    private Grid<Book> grid = new Grid(Book.class);

    private TextField title = new TextField("Название");
    private TextField bookAuthor = new TextField("Автор книги");
    private TextField bookGenre = new TextField("Жанр книги");
    private TextField publisher = new TextField("Издательство");
    private TextField year = new TextField("Год");
    private TextField city = new TextField("Город");

    private Button add = new Button("Добавить", e -> insertForm());
    private Button delete = new Button("Удалить", e-> deleteForm());

    private Button save = new Button("OK", e -> saveAuthor());
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
        grid.setColumns("title", "bookAuthor", "bookGenre", "publisher", "year", "city");

        binder.bindInstanceFields(this);

        vl.addComponents(add,delete);
        hl1.addComponents(grid,vl);
        hl2.addComponents(save,cancel);
        addComponents(hl1, title, bookAuthor, bookGenre, publisher, year, city, hl2);
    }

    private void updateGrid() {
        List<Book> books = service.findAll();
        grid.setItems(books);
        setFormVisible(false);
    }

    private void updateForm() {
        addOrUpdateFlag = true;

        if (grid.asSingleSelect().isEmpty()) {
            setFormVisible(false);
        } else {
            delete.setEnabled(true);
            book = grid.asSingleSelect().getValue();
            binder.setBean(book);
            setFormVisible(true);
        }
    }

    private void insertForm() {
        addOrUpdateFlag = false;

        title.clear();
        bookAuthor.clear();
        bookGenre.clear();
        publisher.clear();
        year.clear();
        city.clear();

        if(!grid.asSingleSelect().isEmpty()){
            grid.select(null);
        }
        setFormVisible(true);

        book = new Book(title.getValue(), Integer.parseInt(bookAuthor.getValue()), Integer.parseInt(bookGenre.getValue()), publisher.getValue(), Integer.parseInt(year.getValue()), city.getValue());
        binder.setBean(book);
    }

    private void deleteForm() {
        if (grid.asSingleSelect().isEmpty()) {
            delete.setEnabled(false);
        }
        else{
            book = grid.asSingleSelect().getValue();
            binder.setBean(book);
            service.delete(book);
        }
        updateGrid();
    }

    private void setFormVisible(boolean visible) {
        if(!visible){
            grid.deselectAll();
            delete.setEnabled(false);
        }
        title.setVisible(visible);
        bookAuthor.setVisible(visible);
        bookGenre.setVisible(visible);
        publisher.setVisible(visible);
        year.setVisible(visible);
        city.setVisible(visible);

        save.setVisible(visible);
        cancel.setVisible(visible);
    }

    private void saveAuthor() {
        if(addOrUpdateFlag){
            service.update(author);
        }
        else {
            service.insert(author);
        }
        updateGrid();
    }
}
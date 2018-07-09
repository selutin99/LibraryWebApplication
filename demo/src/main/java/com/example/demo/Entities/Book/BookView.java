package com.example.demo.Entities.Book;

import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
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


    private TextField name = new TextField("Название");
    private TextField lastName = new TextField("Автор книги");
    private TextField title = new TextField("Жанр книги");

    private NativeSelect<String> publisher = new NativeSelect<>("Издательство");
    private TextField year = new TextField("Год");
    private TextField city = new TextField("Город");


    private Button add = new Button("Добавить", e -> insertForm());
    private Button delete = new Button("Удалить", e-> deleteForm());

    private Button save = new Button("OK", e -> saveAuthor());
    private Button cancel = new Button("Отменить", e-> setFormVisible(false));


    private TextField filter = new TextField();
    private Button clearFilterTextBtn = new Button(VaadinIcons.CLOSE);

    private RadioButtonGroup<String> group = new RadioButtonGroup<>();

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    @PostConstruct
    void init() {
        HorizontalLayout hl1 = new HorizontalLayout();
        HorizontalLayout hl2 = new HorizontalLayout();

        HorizontalLayout hlForRadioButton = new HorizontalLayout();

        VerticalLayout vlForButton1 = new VerticalLayout();
        VerticalLayout vlForButton2 = new VerticalLayout();
        HorizontalLayout hlForButton =  new HorizontalLayout();

        vlForButton1.addComponents(name, lastName,title);
        vlForButton2.addComponents(publisher,year,city);

        hlForButton.addComponents(vlForButton1,vlForButton2);

        //Значения из перечисления Publishers
        publisher.setItems(String.valueOf(Publishers.Москва), String.valueOf(Publishers.Питер),String.valueOf(Publishers.Oreily));
        publisher.setEmptySelectionAllowed(false);

        group.setItems("По имени", "По автору", "По издательству");
        group.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
        group.setSelectedItem("По имени");
        group.addValueChangeListener(e-> {
            switch (e.getValue()) {
                case "По имени":
                    chooseFilter("По имени");
                    break;
                case "По автору":
                    chooseFilter("По автору");
                    break;
                case "По издательству":
                    chooseFilter("По издательству");
                    break;
                default:
                    chooseFilter("По имени");
            }
        });
        filter.setPlaceholder("Фильтрация записей");
        hlForRadioButton.addComponents(group);

        VerticalLayout vl = new VerticalLayout();

        updateGrid();

        delete.setEnabled(false);

        filter.addValueChangeListener(e->updateGridFilterName());
        filter.setValueChangeMode(ValueChangeMode.LAZY);

        clearFilterTextBtn.addClickListener(e -> filter.clear());

        grid.addSelectionListener(e -> updateForm());
        grid.setColumns("name", "lastName", "title", "publisher", "year", "city");

        binder.bindInstanceFields(this);

        vl.addComponents(add,delete);
        hl1.addComponents(grid,vl);
        hl2.addComponents(save,cancel);

        vlForButton1.addComponent(hl2);

        CssLayout filtering = new CssLayout();
        filtering.addComponents(filter, clearFilterTextBtn);
        filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        addComponents(filtering, hlForRadioButton, hl1, hlForButton);
    }

    private void updateGrid() {
        List<Book> books = service.findAll();
        grid.setItems(books);
        setFormVisible(false);
    }

    private void updateGridFilterName() {
        List<Book> books = service.findAllByName(filter.getValue());
        grid.setItems(books);
        setFormVisible(false);
    }

    private void updateGridFilterPublisher() {
        List<Book> books = service.findAllByPublisher(filter.getValue());
        grid.setItems(books);
        setFormVisible(false);
    }

    private void updateGridFilterAuthor() {
        List<Book> books = service.findAllByAuthor(filter.getValue());
        grid.setItems(books);
        setFormVisible(false);
    }

    private void chooseFilter(String id){
        switch (id){
            case "По имени":
                filter.addValueChangeListener(e->updateGridFilterName());
                break;
            case "По автору":
                filter.addValueChangeListener(e->updateGridFilterAuthor());
                break;
            case "По издательству":
                filter.addValueChangeListener(e->updateGridFilterPublisher());
                break;
            default:
                filter.addValueChangeListener(e->updateGridFilterName());
        }
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
            year.setValue(year.getValue().substring(0,4));
        }
    }

    private void insertForm() {
        addOrUpdateFlag = false;

        name.clear();
        lastName.clear();
        title.clear();
        publisher.clear();
        year.clear();
        city.clear();

        if(!grid.asSingleSelect().isEmpty()){
            grid.select(null);
        }
        setFormVisible(true);
        publisher.setValue("Москва");

        book = new Book(name.getValue(), lastName.getValue(), title.getValue(), publisher.getValue(), year.getValue(), city.getValue());
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
        name.setVisible(visible);
        lastName.setVisible(visible);
        title.setVisible(visible);
        publisher.setVisible(visible);
        year.setVisible(visible);
        city.setVisible(visible);

        save.setVisible(visible);
        cancel.setVisible(visible);
    }

    private void saveAuthor() {
        String idAuthor="", idGenre="";
        String yearValidate = year.getValue();
        try{
            int yyyy = Integer.parseInt(yearValidate);
            if(yyyy<=999 || yyyy>=9999){
                Notification.show("Неверное значение!");
                return;
            }
        }
        catch (NumberFormatException e){
            Notification.show("Неверное значение!");
            return;
        }
        if(name.getValue().equals("") || city.getValue().equals("")){
            Notification.show("Введите не пустое значение!");
            return;
        }
        else {
            try {
                idAuthor = service.getAuthorID(lastName.getValue());
                idGenre = service.getGenreID(title.getValue());
            }
            catch (Exception e){
                Notification.show("Проверьте введённые данные.");
                return;
            }

            if (addOrUpdateFlag) {
                try {
                    service.update(book, idAuthor, idGenre);
                }
                catch(Exception e){
                    Notification.show("Обновление не удалось. Проверьте введённые данные.");
                    return;
                }
            }
            else {
                try {
                    service.insert(book, idAuthor, idGenre);
                }
                catch(Exception e){
                    Notification.show("Добавление не удалось. Проверьте введённые данные.");
                    return;
                }
            }
            updateGrid();
        }
    }
}
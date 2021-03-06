package com.example.demo;

import com.example.demo.Entities.Author.AuthorView;
import com.example.demo.Entities.Book.BookView;
import com.example.demo.Entities.Genre.GenreView;
import com.example.demo.Entities.Statistic.StatisticView;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;


@SpringUI
@Theme("valo")
public class MainUI extends UI {

    MainViewDisplay mainContent;

    public MainUI( MainViewDisplay mainContent, SpringNavigator navigator) {
        this.mainContent = mainContent;
        navigator.setErrorView(StartView.class);
    }

    @Override
    protected void init(VaadinRequest request) {
        addStyleName("v-scrollable");
        setHeight("100%");

        setContent(
                new MHorizontalLayout()
                        .add(createNavigationBar())
                        .expand(mainContent)
                        .withFullHeight()
        );
    }

    private Component createNavigationBar() {
        MVerticalLayout m = new MVerticalLayout().withWidth("300px");
        m.addComponent(createNavButton("Список авторов", AuthorView.class));
        m.addComponent(createNavButton("Список жанров", GenreView.class));
        m.addComponent(createNavButton("Список книг", BookView.class));
        m.addComponent(createNavButton("Статистика", StatisticView.class));
        return m;
    }

    private Component createNavButton(String first, Class<? extends View> aClass) {
        Button button = new Button(first);
        button.addClickListener(e->getNavigator().navigateTo(aClass.getSimpleName().replaceAll("View", "").toLowerCase()));
        button.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        button.addStyleName(ValoTheme.BUTTON_LINK);
        return button;
    }

}

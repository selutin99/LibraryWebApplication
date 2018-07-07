package com.example.demo;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import javax.annotation.PostConstruct;


@SpringComponent
@UIScope
@SpringView
public class StartView extends VerticalLayout implements View {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    @PostConstruct
    void init() {
        addComponents(new Label("Система ввода и отображения информации о книгах библиотеки."),
                new Label("Воспользуйтесь навигационным меню для просмотра (изменения) информации."));
    }
}

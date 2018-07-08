package com.example.demo.Entities.Statistic;

import com.example.demo.Entities.Genre.Genre;
import com.example.demo.Entities.Genre.GenreService;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringView
public class StatisticView extends VerticalLayout implements View {

    @Autowired
    private StatisticService service;

    private Statistic statistic;
    private Binder<Statistic> binder = new Binder<>(Statistic.class);

    private Grid<Statistic> grid = new Grid(Statistic.class);

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    @PostConstruct
    void init() {
        updateGrid();
        addComponents(grid);
    }

    private void updateGrid() {
        List<Statistic> statistics = service.findAll();
        grid.setItems(statistics);
    }
}

package com.premerleagueapp.premerleagueapp;

import com.premerleagueapp.premerleagueapp.controller.TeamController;
import com.premerleagueapp.premerleagueapp.domain.Team;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class TeamView extends VerticalLayout {

    @Autowired
    private TeamController teamController;

    private final Grid grid = new Grid<>(Team.class);

    public TeamView() {
        grid.getColumns();
        add(grid);
        setSizeFull();
    }

    public void refresh() {
        grid.setItems();
    }

}

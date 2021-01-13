package com.premerleagueapp.premerleagueapp;

import com.premerleagueapp.premerleagueapp.domain.League;
import com.premerleagueapp.premerleagueapp.service.LeagueService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.context.annotation.Bean;

@Route
public class LeagueView extends VerticalLayout {

    private LeagueService leagueService;
    private Grid grid = new Grid<>(League.class);

    public LeagueView(LeagueService leagueService) {
        this.leagueService = leagueService;
        grid.getColumns();
        add(grid);
        setSizeFull();
        refresh();
    }

    public void refresh() {
        grid.setItems(leagueService.getAllLeagues());
        grid.removeColumnByKey("teams");
    }

}

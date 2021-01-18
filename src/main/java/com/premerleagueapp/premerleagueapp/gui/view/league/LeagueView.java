package com.premerleagueapp.premerleagueapp.gui.view.league;

import com.premerleagueapp.premerleagueapp.backend.domain.League;
import com.premerleagueapp.premerleagueapp.backend.service.LeagueService;
import com.premerleagueapp.premerleagueapp.gui.MainLayout;
import com.premerleagueapp.premerleagueapp.gui.view.league.LeagueForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="league", layout = MainLayout.class)
@PageTitle("Leagues | Premier League APP")
public class LeagueView extends VerticalLayout {

    private final LeagueForm form;
    Grid<League> grid = new Grid<>(League.class);
    TextField filterText = new TextField();

    private LeagueService leagueService;

    public LeagueView(LeagueService leagueService) {
        this.leagueService = leagueService;
        addClassName("league-view");
        setSizeFull();
        configureGrid();

        form = new LeagueForm();
        form.addListener(LeagueForm.SaveEvent.class, this::saveLeague);
        form.addListener(LeagueForm.DeleteEvent.class, this::deleteLeague);
        form.addListener(LeagueForm.CloseEvent.class, e -> closeEditor());

        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.addClassName("content");
        grid.setWidth("75%");
        form.setWidth("25%");
        content.setSizeFull();

        add(getToolbar(), content);
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassName("league-grid");
        grid.setSizeFull();
        grid.setColumns("id", "name", "description");
        grid.asSingleSelect().addValueChangeListener(event ->
                editLeague(event.getValue()));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by Name/Description...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addLeagueButton = new Button("Add league");
        addLeagueButton.addClickListener(click -> addLeague());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addLeagueButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void updateList() {
        grid.setItems(leagueService.findAll(filterText.getValue()));
    }

    public void editLeague(League league) {
        if (league == null) {
            closeEditor();
        } else {
            form.setLeague(league);
            form.setVisible(true);
        }
    }

    private void closeEditor() {
        form.setLeague(null);
        form.setVisible(false);
    }

    private void saveLeague(LeagueForm.SaveEvent event) {
        leagueService.saveLeague(event.getLeague());
        updateList();
        closeEditor();
    }

    private void deleteLeague(LeagueForm.DeleteEvent event) {
        leagueService.deleteLeague(event.getLeague().getId());
        updateList();
        closeEditor();
    }

    void addLeague() {
        grid.asSingleSelect().clear();
        editLeague(new League());
    }
}

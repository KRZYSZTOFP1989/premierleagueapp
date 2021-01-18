package com.premerleagueapp.premerleagueapp.gui.view.team;

import com.premerleagueapp.premerleagueapp.backend.domain.League;
import com.premerleagueapp.premerleagueapp.backend.domain.Team;
import com.premerleagueapp.premerleagueapp.backend.service.LeagueService;
import com.premerleagueapp.premerleagueapp.backend.service.TeamService;;
import com.premerleagueapp.premerleagueapp.gui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="teams", layout = MainLayout.class)
@PageTitle("Teams | Premier League APP")
public class TeamView extends VerticalLayout {

    private final TeamForm form;
    Grid<Team> grid = new Grid<>(Team.class);
    TextField filterText = new TextField();

    private TeamService teamService;

    public TeamView(TeamService teamService,
                    LeagueService leagueService) {
        this.teamService = teamService;
        addClassName("team-view");
        setSizeFull();
        configureGrid();

        form = new TeamForm(leagueService.getAllLeagues());
        form.addListener(TeamForm.SaveEvent.class, this::saveTeam);
        form.addListener(TeamForm.DeleteEvent.class, this::deleteTeam);
        form.addListener(TeamForm.CloseEvent.class, e -> closeEditor());

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
        grid.addClassName("team-grid");
        grid.setSizeFull();
        grid.removeColumnByKey("league");
        grid.setColumns("id", "name", "founded", "ground", "description");
        grid.addColumn(team -> {
            League league = team.getLeague();
            return league == null ? "-" : league.getName();
        }).setHeader("League");
        grid.asSingleSelect().addValueChangeListener(event ->
                editTeam(event.getValue()));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by Name/Description...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addTeamButton = new Button("Add team");
        addTeamButton.addClickListener(click -> addTeam());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addTeamButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void updateList() {
        grid.setItems(teamService.findAll(filterText.getValue()));
    }

    public void editTeam(Team team) {
        if (team == null) {
            closeEditor();
        } else {
            form.setTeam(team);
            form.setVisible(true);
        }
    }

    private void closeEditor() {
        form.setTeam(null);
        form.setVisible(false);
    }

    private void saveTeam(TeamForm.SaveEvent event) {
        teamService.saveTeam(event.getTeam());
        updateList();
        closeEditor();
    }

    private void deleteTeam(TeamForm.DeleteEvent event) {
        teamService.deleteTeam(event.getTeam().getId());
        updateList();
        closeEditor();
    }

    void addTeam() {
        grid.asSingleSelect().clear();
        editTeam(new Team());
    }
}
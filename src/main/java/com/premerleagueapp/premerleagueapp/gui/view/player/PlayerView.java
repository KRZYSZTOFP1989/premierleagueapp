package com.premerleagueapp.premerleagueapp.gui.view.player;

import com.premerleagueapp.premerleagueapp.backend.domain.Player;
import com.premerleagueapp.premerleagueapp.backend.domain.Position;
import com.premerleagueapp.premerleagueapp.backend.domain.Team;
import com.premerleagueapp.premerleagueapp.backend.service.PlayerService;
import com.premerleagueapp.premerleagueapp.backend.service.PositionService;
import com.premerleagueapp.premerleagueapp.backend.service.TeamService;
import com.premerleagueapp.premerleagueapp.gui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="players", layout = MainLayout.class)
@PageTitle("Players | Premier League APP")
public class PlayerView extends VerticalLayout {

    private final PlayerForm form;
    Grid<Player> grid = new Grid<>(Player.class);
    TextField filterText = new TextField();

    private PlayerService playerService;

    public PlayerView(PlayerService playerService,
                      TeamService teamService, PositionService positionService) {
        this.playerService = playerService;
        addClassName("player-view");
        setSizeFull();
        configureGrid();

        form = new PlayerForm(teamService.getAllTeams(), positionService.getAllPositions());
        form.addListener(PlayerForm.SaveEvent.class, this::savePlayer);
        form.addListener(PlayerForm.DeleteEvent.class, this::deletePlayer);
        form.addListener(PlayerForm.CloseEvent.class, e -> closeEditor());

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
        grid.addClassName("player-grid");
        grid.setSizeFull();
        grid.removeColumnByKey("team");
        grid.removeColumnByKey("position");
        grid.setColumns("id", "name", "surname", "dateOfBirth");
        grid.addColumn(player -> {
            Team team = player.getTeam();
            return team == null ? "-" : team.getName();
        }).setHeader("Team");
        grid.addColumn(player -> {
            Position position = player.getPosition();
            return position == null ? "-" : position.getName();
        }).setHeader("Position");
        grid.asSingleSelect().addValueChangeListener(event ->
                editPlayer(event.getValue()));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by Name/Surname...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addTeamButton = new Button("Add player");
        addTeamButton.addClickListener(click -> addPlayer());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addTeamButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void updateList() {
        grid.setItems(playerService.findAll(filterText.getValue()));
    }

    public void editPlayer(Player player) {
        if (player == null) {
            closeEditor();
        } else {
            form.setPlayer(player);
            form.setVisible(true);
        }
    }

    private void closeEditor() {
        form.setPlayer(null);
        form.setVisible(false);
    }

    private void savePlayer(PlayerForm.SaveEvent event) {
        playerService.savePlayer(event.getPlayer());
        updateList();
        closeEditor();
    }

    private void deletePlayer(PlayerForm.DeleteEvent event) {
        playerService.deletePlayer(event.getPlayer().getId());
        updateList();
        closeEditor();
    }

    void addPlayer() {
        grid.asSingleSelect().clear();
        editPlayer(new Player());
    }
}

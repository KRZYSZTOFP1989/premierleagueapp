package com.premerleagueapp.premerleagueapp.gui.view.player;

import com.premerleagueapp.premerleagueapp.backend.domain.Player;
import com.premerleagueapp.premerleagueapp.backend.domain.Position;
import com.premerleagueapp.premerleagueapp.backend.domain.Team;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;

import com.vaadin.flow.shared.Registration;

import java.util.List;

public class PlayerForm extends FormLayout {

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    BeanValidationBinder<Player> binder = new BeanValidationBinder<>(Player.class);

    TextField name = new TextField("Name");
    TextField surname = new TextField("Surname");
    TextField date_of_birth = new TextField("Date of birth");
    ComboBox<Position> position = new ComboBox<>("Position");
    ComboBox<Team> team = new ComboBox<>("Team");

    private Player player;

    public PlayerForm(List<Team> teams, List<Position> positions) {
        addClassName("player-form");
        binder.bindInstanceFields(this);
        team.setItems(teams);
        team.setItemLabelGenerator(Team::getName);
        position.setItems(positions);
        position.setItemLabelGenerator(Position::getName);

        add(
                name,
                surname,
                date_of_birth,
                position,
                team,
                createButtonsLayout()
        );
    }

    public void setPlayer(Player player) {
        this.player = player;
        binder.readBean(player);
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, player)));
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(evt -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {

        try {
            binder.writeBean(player);
            fireEvent(new SaveEvent(this, player));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public static abstract class PlayerFormEvent extends ComponentEvent<PlayerForm> {
        private final Player player;

        protected PlayerFormEvent(PlayerForm source, Player player) {
            super(source, false);
            this.player = player;
        }

        public Player getPlayer() {
            return player;
        }
    }

    public static class SaveEvent extends PlayerFormEvent {
        SaveEvent(PlayerForm source, Player player) {
            super(source, player);
        }
    }

    public static class DeleteEvent extends PlayerFormEvent {
        DeleteEvent(PlayerForm source,Player player) {
            super(source, player);
        }

    }

    public static class CloseEvent extends PlayerFormEvent {
        CloseEvent(PlayerForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
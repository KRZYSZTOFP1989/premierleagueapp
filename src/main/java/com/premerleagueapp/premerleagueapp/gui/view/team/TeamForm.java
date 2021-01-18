package com.premerleagueapp.premerleagueapp.gui.view.team;

import com.premerleagueapp.premerleagueapp.backend.domain.League;
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
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class TeamForm extends FormLayout {

    TextField name = new TextField("Name");
    TextField description = new TextField("Description");
    TextField founded = new TextField("Founded");
    TextField ground = new TextField("Ground");
    ComboBox<League> league = new ComboBox<>("League");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<Team> binder = new BeanValidationBinder<>(Team.class);
    private Team team;

    public TeamForm(List<League> leagues) {
        addClassName("team-form");

        binder.bindInstanceFields(this);
        league.setItems(leagues);
        league.setItemLabelGenerator(League::getName);

        add(
                name,
                description,
                founded,
                ground,
                league,
                createButtonsLayout()
        );
    }

    public void setTeam(Team team) {
        this.team = team;
        binder.readBean(team);
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, team)));
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(evt -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {

        try {
            binder.writeBean(team);
            fireEvent(new SaveEvent(this, team));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public static abstract class TeamFormEvent extends ComponentEvent<TeamForm> {
        private final Team team;

        protected TeamFormEvent(TeamForm source, Team team) {
            super(source, false);
            this.team = team;
        }

        public Team getTeam() {
            return team;
        }
    }

    public static class SaveEvent extends TeamFormEvent {
        SaveEvent(TeamForm source, Team team) {
            super(source, team);
        }
    }

    public static class DeleteEvent extends TeamFormEvent {
        DeleteEvent(TeamForm source,Team team) {
            super(source, team);
        }

    }

    public static class CloseEvent extends TeamFormEvent {
        CloseEvent(TeamForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}


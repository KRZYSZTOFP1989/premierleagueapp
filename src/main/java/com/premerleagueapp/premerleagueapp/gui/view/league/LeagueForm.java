package com.premerleagueapp.premerleagueapp.gui.view.league;

import com.premerleagueapp.premerleagueapp.backend.domain.League;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class LeagueForm extends FormLayout {

    TextField name = new TextField("Name");
    TextField description = new TextField("Description");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<League> binder = new BeanValidationBinder<>(League.class);
    private League league;

    public LeagueForm() {
        addClassName("league-form");

        binder.bindInstanceFields(this);
        add(
                name,
                description,
                createButtonsLayout()
        );
    }

    public void setLeague(League league) {
        this.league = league;
        binder.readBean(league);
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, league)));
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(evt -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {

        try {
            binder.writeBean(league);
            fireEvent(new SaveEvent(this, league));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public static abstract class LeagueFormEvent extends ComponentEvent<LeagueForm> {
        private final League league;

        protected LeagueFormEvent(LeagueForm source, League league) {
            super(source, false);
            this.league = league;
        }

        public League getLeague() {
            return league;
        }
    }

    public static class SaveEvent extends LeagueFormEvent {
        SaveEvent(LeagueForm source, League league) {
            super(source, league);
        }
    }

    public static class DeleteEvent extends LeagueFormEvent {
        DeleteEvent(LeagueForm source, League league) {
            super(source, league);
        }

    }

    public static class CloseEvent extends LeagueFormEvent {
        CloseEvent(LeagueForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}

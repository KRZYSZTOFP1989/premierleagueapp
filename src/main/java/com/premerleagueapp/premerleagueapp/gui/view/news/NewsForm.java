package com.premerleagueapp.premerleagueapp.gui.view.news;

import com.premerleagueapp.premerleagueapp.backend.domain.News;
import com.premerleagueapp.premerleagueapp.backend.domain.User;
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

public class NewsForm extends FormLayout {

    TextField title = new TextField("Title");
    TextField date = new TextField("Date");
    TextField content = new TextField("Content");
    TextField source = new TextField("Source");
    ComboBox<User> user = new ComboBox<>("Author");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<News> binder = new BeanValidationBinder<>(News.class);
    private News news;

    public NewsForm(List<User> users) {
        addClassName("user-form");

        binder.bindInstanceFields(this);
        user.setItems(users);
        user.setItemLabelGenerator(User::getNickname);
        add(
                title,
                content,
                source,
                user,
                createButtonsLayout()
        );
    }

    public void setNews(News news) {
        this.news = news;
        binder.readBean(news);
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, news)));
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(evt -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {

        try {
            binder.writeBean(news);
            fireEvent(new SaveEvent(this, news));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public static abstract class NewsFormEvent extends ComponentEvent<NewsForm> {
        private final News news;

        protected NewsFormEvent(NewsForm source, News news) {
            super(source, false);
            this.news = news;
        }

        public News getNews() {
            return news;
        }
    }

    public static class SaveEvent extends NewsFormEvent {
        SaveEvent(NewsForm source, News news) {
            super(source, news);
        }
    }

    public static class DeleteEvent extends NewsFormEvent {
        DeleteEvent(NewsForm source,News news) {
            super(source, news);
        }

    }

    public static class CloseEvent extends NewsFormEvent {
        CloseEvent(NewsForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}




package com.premerleagueapp.premerleagueapp.gui.view.news;

import com.premerleagueapp.premerleagueapp.backend.domain.News;
import com.premerleagueapp.premerleagueapp.backend.domain.User;
import com.premerleagueapp.premerleagueapp.backend.service.NewsService;
import com.premerleagueapp.premerleagueapp.backend.service.UserService;
import com.premerleagueapp.premerleagueapp.gui.MainLayout;
import com.premerleagueapp.premerleagueapp.gui.view.news.NewsForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="news", layout = MainLayout.class)
@PageTitle("News | Premier League APP")
public class NewsView extends VerticalLayout {

    private final NewsForm form;
    Grid<News> grid = new Grid<>(News.class);
    TextField filterText = new TextField();

    private NewsService newsService;

    public NewsView(NewsService newsService,
                    UserService userService) {
        this.newsService = newsService;
        addClassName("news-view");
        setSizeFull();
        configureGrid();

        form = new NewsForm(userService.getAllUsers());
        form.addListener(NewsForm.SaveEvent.class, this::saveNews);
        form.addListener(NewsForm.DeleteEvent.class, this::deleteNews);
        form.addListener(NewsForm.CloseEvent.class, e -> closeEditor());

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
        grid.addClassName("news-grid");
        grid.setSizeFull();
        grid.removeColumnByKey("user");
        grid.setColumns("id", "title", "content", "source");
        grid.addColumn(news -> {
            User user = news.getUser();
            return user == null ? "-" : user.getNickname();
        }).setHeader("User");
        grid.asSingleSelect().addValueChangeListener(event ->
                editTeam(event.getValue()));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by Title/Content...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addNewsButton = new Button("Add news");
        addNewsButton.addClickListener(click -> addTeam());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addNewsButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void updateList() {
        grid.setItems(newsService.findAll(filterText.getValue()));
    }

    public void editTeam(News news) {
        if (news == null) {
            closeEditor();
        } else {
            form.setNews(news);
            form.setVisible(true);
        }
    }

    private void closeEditor() {
        form.setNews(null);
        form.setVisible(false);
    }

    private void saveNews(NewsForm.SaveEvent event) {
        newsService.saveNews(event.getNews());
        updateList();
        closeEditor();
    }

    private void deleteNews(NewsForm.DeleteEvent event) {
        newsService.deleteNews(event.getNews().getId());
        updateList();
        closeEditor();
    }

    void addTeam() {
        grid.asSingleSelect().clear();
        editTeam(new News());
    }
}

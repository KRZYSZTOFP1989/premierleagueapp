package com.premerleagueapp.premerleagueapp.gui;

import com.premerleagueapp.premerleagueapp.gui.view.league.LeagueView;
import com.premerleagueapp.premerleagueapp.gui.view.news.NewsView;
import com.premerleagueapp.premerleagueapp.gui.view.player.PlayerView;
import com.premerleagueapp.premerleagueapp.gui.view.team.TeamView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Premier League App");
        logo.addClassName("logo");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);

        header.setDefaultVerticalComponentAlignment(
                FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassName("header");

        addToNavbar(header);

    }

    private void createDrawer() {
        RouterLink listLink = new RouterLink("League", LeagueView.class);
        RouterLink listLink1 = new RouterLink("Team", TeamView.class);
        RouterLink listLink2 = new RouterLink("Player", PlayerView.class);
        RouterLink listLink3 = new RouterLink("News", NewsView.class);
        listLink.setHighlightCondition(HighlightConditions.sameLocation());
        listLink1.setHighlightCondition(HighlightConditions.sameLocation());
        listLink2.setHighlightCondition(HighlightConditions.sameLocation());
        listLink3.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(listLink, listLink1, listLink2, listLink3));
    }
}

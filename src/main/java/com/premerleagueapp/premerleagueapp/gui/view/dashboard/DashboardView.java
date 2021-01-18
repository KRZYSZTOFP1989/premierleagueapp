package com.premerleagueapp.premerleagueapp.gui.view.dashboard;

import com.premerleagueapp.premerleagueapp.backend.service.LeagueService;
import com.premerleagueapp.premerleagueapp.backend.service.NewsService;
import com.premerleagueapp.premerleagueapp.backend.service.PlayerService;
import com.premerleagueapp.premerleagueapp.backend.service.TeamService;
import com.premerleagueapp.premerleagueapp.gui.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Dashboard | Premier League App")
@Route(value = "", layout = MainLayout.class)
public class DashboardView extends VerticalLayout {

    private LeagueService leagueService;
    private TeamService teamService;
    private PlayerService playerService;
    private NewsService newsService;

    public DashboardView(LeagueService leagueService, TeamService teamService, PlayerService
                         playerService, NewsService newsService) {
        this.leagueService = leagueService;
        this.teamService = teamService;
        this.playerService = playerService;
        this.newsService = newsService;
        addClassName("dashboard-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }
}

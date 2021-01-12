package com.premerleagueapp.premerleagueapp.service;

import com.premerleagueapp.premerleagueapp.domain.League;

import java.util.List;

public interface LeagueService {

    List<League> getAllLeagues();
    League findById(final Long leagueId) throws Exception;
    void deleteLeague(final Long leagueId);
    League saveLeague(final League league);
    League updateLeague(final League league) throws Exception;
}

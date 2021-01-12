package com.premerleagueapp.premerleagueapp.service;

import com.premerleagueapp.premerleagueapp.domain.Team;

import java.util.List;

public interface TeamService {

    List<Team> getAllTeams();
    Team findById(final Long teamId) throws Exception;
    void deleteTeam(final Long teamId);
    Team saveTeam(final Team team);
    Team updateTeam(final Team team) throws Exception;
}

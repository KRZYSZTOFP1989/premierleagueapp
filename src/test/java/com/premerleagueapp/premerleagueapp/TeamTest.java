package com.premerleagueapp.premerleagueapp;

import com.premerleagueapp.premerleagueapp.backend.domain.League;
import com.premerleagueapp.premerleagueapp.backend.domain.Team;
import com.premerleagueapp.premerleagueapp.backend.repository.LeagueRepository;
import com.premerleagueapp.premerleagueapp.backend.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TeamTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired LeagueRepository leagueRepository;

    League league = new League();
    Team team = new Team();
    Team team1 = new Team();
    Team team2 = new Team();

    @Test
    public void createTeamTest() {

        //Given

        //When
        team.setLeague(league);
        teamRepository.save(team);
        Long teamId = team.getId();

        //Then
        assertTrue(teamRepository.findById(teamId).isPresent());

        //CleanUp
        teamRepository.deleteById(teamId);
    }

    @Test
    public void AddTeamToGroupLeague() {

        //Given
        List<Team> teamList = new ArrayList<>();
        teamList.add(team1);
        teamList.add(team2);

        //When
        league.setTeams(teamList);
        team1.setLeague(league);
        team2.setLeague(league);
        teamRepository.save(team1);
        teamRepository.save(team2);
        leagueRepository.save(league);
        Long teamId = team.getId();

        //Then
        assertTrue(league.getTeams().contains(team1));
        assertTrue(league.getTeams().contains(team2));

        //CleanUp
        teamRepository.deleteById(teamId);
    }
}
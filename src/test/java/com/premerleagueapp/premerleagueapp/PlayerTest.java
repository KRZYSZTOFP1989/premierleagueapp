package com.premerleagueapp.premerleagueapp;

import com.premerleagueapp.premerleagueapp.backend.domain.Player;
import com.premerleagueapp.premerleagueapp.backend.domain.Team;
import com.premerleagueapp.premerleagueapp.backend.repository.PlayerRepository;
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
public class PlayerTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

    Team team = new Team();
    Player player = new Player();
    Player player1 = new Player();

    @Test
    public void createTeamTest() {

        //Given

        //When
        player.setTeam(team);
        playerRepository.save(player);
        Long playerId = player.getId();

        //Then
        assertTrue(playerRepository.findById(playerId).isPresent());

        //CleanUp
        playerRepository.deleteById(playerId);
    }

    @Test
    public void AddTeamToGroupLeague() {

        //Given
        List<Player> playerList = new ArrayList<>();
        playerList.add(player);
        playerList.add(player1);

        //When
        team.setPlayers(playerList);
        player.setTeam(team);
        player1.setTeam(team);
        playerRepository.save(player);
        playerRepository.save(player1);
        teamRepository.save(team);
        Long playerId = player.getId();

        //Then
        assertTrue(team.getPlayers().contains(player));
        assertTrue(team.getPlayers().contains(player1));

        //CleanUp
        playerRepository.deleteById(playerId);
    }
}



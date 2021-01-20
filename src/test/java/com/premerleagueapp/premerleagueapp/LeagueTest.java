package com.premerleagueapp.premerleagueapp;

import com.premerleagueapp.premerleagueapp.backend.domain.League;
import com.premerleagueapp.premerleagueapp.backend.domain.Team;
import com.premerleagueapp.premerleagueapp.backend.repository.LeagueRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.helger.commons.mock.CommonsAssert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LeagueTest {

    @Autowired
    private LeagueRepository leagueRepository;

    League league = new League();

    @Test
    public void shouldSaveGroupInDatabase() {
        //Given
        league.setName("Premier League");

        //When
        League savedLeague = leagueRepository.save(league);

        //Then
        assertNotNull(savedLeague.getId());

        //CleanUp
        leagueRepository.deleteById(league.getId());
    }

    @Test
    public void shouldSetName() {
        //Given
        String givenName = "Premier League";

        //When
        league.setName(givenName);
        League savedLeague = leagueRepository.save(league);

        //Then
        assertEquals(givenName, savedLeague.getName());

        //CleanUp
        leagueRepository.deleteById(league.getId());
    }

    @Test
    public void shouldSetDescription() {
        //Given
        String givenDescription = "Premier League is the best...";

        //When
        league.setDescription(givenDescription);
        League savedLeague = leagueRepository.save(league);

        //Then
        assertEquals(givenDescription, savedLeague.getDescription());

        //CleanUp
        leagueRepository.deleteById(league.getId());
    }

    @Test
    public void shouldSetTeams() {

        //Given
        Team team = new Team();
        team.setName("Arsenal FC");
        Team team1 = new Team();
        team1.setName("Manchester City");
        Team team2 = new Team();
        team2.setName("Chelsea FC");

        //When
        league.getTeams().add(team);
        league.getTeams().add(team1);
        league.getTeams().add(team2);

        League savedLeague = leagueRepository.save(league);

        //Then
        assertTrue(savedLeague.getTeams().contains(team));
        assertTrue(savedLeague.getTeams().contains(team1));
        assertTrue(savedLeague.getTeams().contains(team2));

        //CleanUp
        leagueRepository.deleteById(league.getId());
    }
}

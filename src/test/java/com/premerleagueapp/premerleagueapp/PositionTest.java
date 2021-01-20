package com.premerleagueapp.premerleagueapp;

import com.premerleagueapp.premerleagueapp.backend.domain.League;
import com.premerleagueapp.premerleagueapp.backend.domain.Player;
import com.premerleagueapp.premerleagueapp.backend.domain.Position;
import com.premerleagueapp.premerleagueapp.backend.domain.Team;
import com.premerleagueapp.premerleagueapp.backend.repository.PositionRepository;
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
public class PositionTest {

    @Autowired
    private PositionRepository positionRepository;

    Position position = new Position();

    @Test
    public void shouldSavePositionInDatabase() {
        //Given
        position.setName("goalkeeper");

        //When
        Position savedPosition = positionRepository.save(position);

        //Then
        assertNotNull(savedPosition.getId());

        //CleanUp
        positionRepository.deleteById(position.getId());
    }

    @Test
    public void shouldSetName() {
        //Given
        String givenName = "goalkeeper";

        //When
        position.setName(givenName);
        Position savedPosition = positionRepository.save(position);

        //Then
        assertEquals(givenName, savedPosition.getName());

        //CleanUp
        positionRepository.deleteById(position.getId());
    }

    @Test
    public void shouldSetPlayers() {

        //Given
        Player player = new Player();
        player.setPosition(player.getPosition());
        Player player1 = new Player();
        player1.setPosition(player.getPosition());

        //When
        position.getPlayers().add(player);
        position.getPlayers().add(player1);

        Position savedPosition = positionRepository.save(position);

        //Then
        assertTrue(savedPosition.getPlayers().contains(player));
        assertTrue(savedPosition.getPlayers().contains(player1));

        //CleanUp
        positionRepository.deleteById(position.getId());
    }

}

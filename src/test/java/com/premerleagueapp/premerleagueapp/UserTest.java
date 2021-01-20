package com.premerleagueapp.premerleagueapp;

import com.premerleagueapp.premerleagueapp.backend.domain.League;
import com.premerleagueapp.premerleagueapp.backend.domain.News;
import com.premerleagueapp.premerleagueapp.backend.domain.Team;
import com.premerleagueapp.premerleagueapp.backend.domain.User;
import com.premerleagueapp.premerleagueapp.backend.repository.UserRepository;
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
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    News news = new News();
    News news1 = new News();
    User user = new User();

    @Test
    public void shouldSaveUserInDatabase() {
        //Given
        user.setNickname("John");

        //When
        User savedUser = userRepository.save(user);

        //Then
        assertNotNull(savedUser.getId());

        //CleanUp
        userRepository.deleteById(user.getId());
    }

    @Test
    public void shouldSetName() {
        //Given
        String givenNickname = "John";

        //When
        user.setNickname(givenNickname);
        User savedUser = userRepository.save(user);

        //Then
        assertEquals(givenNickname, savedUser.getNickname());

        //CleanUp
        userRepository.deleteById(user.getId());
    }

    @Test
    public void shouldSetNews() {

        //Given
        news.setTitle("Arsenal FC want buy news striker");
        news1.setTitle("Chelsea FC want buy news striker");

        //When
        user.getNews().add(news);
        user.getNews().add(news1);

        User savedUser = userRepository.save(user);

        //Then
        assertTrue(savedUser.getNews().contains(news));
        assertTrue(savedUser.getNews().contains(news));

        //CleanUp
        userRepository.deleteById(user.getId());
    }


}

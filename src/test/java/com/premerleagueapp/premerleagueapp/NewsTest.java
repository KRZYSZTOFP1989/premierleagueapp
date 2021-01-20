package com.premerleagueapp.premerleagueapp;

import com.premerleagueapp.premerleagueapp.backend.domain.News;
import com.premerleagueapp.premerleagueapp.backend.domain.User;
import com.premerleagueapp.premerleagueapp.backend.repository.NewsRepository;
import com.premerleagueapp.premerleagueapp.backend.repository.UserRepository;
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
public class NewsTest {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    UserRepository userRepository;

    News news = new News();
    News news1 = new News();
    User user = new User();
    User user1 = new User();


    @Test
    public void createNewsTest() {

        //Given

        //When
        news.setUser(user);
        newsRepository.save(news);
        Long userId = user.getId();

        //Then
        assertTrue(newsRepository.findById(userId).isPresent());

        //CleanUp
        newsRepository.deleteById(userId);
    }

    @Test
    public void AddTeamToGroupLeague() {

        //Given
        List<News> newsList = new ArrayList<>();
        newsList.add(news);
        newsList.add(news1);

        //When
        user.setNews(newsList);
        news.setUser(user);
        news1.setUser(user1);
        newsRepository.save(news);
        newsRepository.save(news1);
        userRepository.save(user);
        userRepository.save(user1);
        Long newsId = news.getId();

        //Then
        assertTrue(user.getNews().contains(news));
        assertTrue(user.getNews().contains(news1));

        //CleanUp
        newsRepository.deleteById(newsId);
    }
}

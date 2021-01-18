package com.premerleagueapp.premerleagueapp.backend.mapper;

import com.premerleagueapp.premerleagueapp.backend.domain.News;
import com.premerleagueapp.premerleagueapp.backend.domain.User;
import com.premerleagueapp.premerleagueapp.backend.dto.NewsDto;
import com.premerleagueapp.premerleagueapp.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class NewsMapper {

    private final UserRepository userRepository;

    public News MapToNews(final NewsDto newsDto) throws Exception {
        News news = new News();
        news.setId(newsDto.getId());
        news.setTitle(newsDto.getTitle());
        news.setContent(newsDto.getContent());
        news.setSource(newsDto.getSource());
        User user = userRepository.findById(newsDto.getUserId()).orElseThrow(() -> new Exception("User id " + newsDto.getUserId() + " not found"));
        news.setUser(user);
        return news;
    }

    public NewsDto MapToNewsDto(final News news) {
        NewsDto newsDto = new NewsDto();
        newsDto.setId(news.getId());
        newsDto.setTitle(news.getTitle());
        newsDto.setContent(news.getContent());
        newsDto.setSource(news.getSource());
        newsDto.setUserId(news.getUser().getId());
        return newsDto;
    }

    public List<NewsDto> MapToNewsDtoList(final List<News> newsList) {
        return newsList.stream()
                .map(this::MapToNewsDto)
                .collect(Collectors.toList());
    }
}

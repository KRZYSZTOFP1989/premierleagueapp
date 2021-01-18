package com.premerleagueapp.premerleagueapp.backend.service;

import com.premerleagueapp.premerleagueapp.backend.domain.News;

import java.util.List;

public interface NewsService {

    List<News> getAllNews();
    News findById(final Long newsId) throws Exception;
    void deleteNews(final Long newsId);
    News saveNews(final News news);
    News updateNews(final News news) throws Exception;
    List<News> findAll(String stringFilter);
}

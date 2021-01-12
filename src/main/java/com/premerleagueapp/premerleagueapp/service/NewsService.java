package com.premerleagueapp.premerleagueapp.service;

import com.premerleagueapp.premerleagueapp.domain.News;

import java.util.List;

public interface NewsService {

    List<News> getAllNews();
    News findById(final Long newsId) throws Exception;
    void deleteNews(final Long newsId);
    News saveNews(final News news);
    News updateNews(final News news) throws Exception;
}

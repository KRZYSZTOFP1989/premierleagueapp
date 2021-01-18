package com.premerleagueapp.premerleagueapp.backend.service;


import com.premerleagueapp.premerleagueapp.backend.domain.News;
import com.premerleagueapp.premerleagueapp.backend.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService{

    private final NewsRepository newsRepository;

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public News findById(final Long newsId) throws Exception {
        return newsRepository.findById(newsId).orElseThrow(()
                -> new Exception("News not found"));
    }

    public List<News> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return newsRepository.findAll();
        } else {
            return newsRepository.search(stringFilter);
        }
    }

    @Override
    public News saveNews(final News news)  {
        return newsRepository.save(news);
    }

    @Override
    public News updateNews(final News news) throws Exception {
        Long id = news.getId();
        Optional<News> idNumber = newsRepository.findById(id);

        if (!idNumber.isPresent()) {
            throw new Exception("The league does not exist!");
        }
        return newsRepository.save(news);
    }

    @Override
    public void deleteNews(final Long newsId) {
        newsRepository.deleteById(newsId);
    }
}

package com.premerleagueapp.premerleagueapp.controller;

import com.premerleagueapp.premerleagueapp.dto.NewsDto;
import com.premerleagueapp.premerleagueapp.mapper.NewsMapper;
import com.premerleagueapp.premerleagueapp.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("v1/")
@RequiredArgsConstructor
public class NewsController {

    private final NewsMapper newsMapper;
    private final NewsService newsService;

    @RequestMapping(method = RequestMethod.GET, value = "/news")
    public List<NewsDto> getNews() {
        return newsMapper.MapToNewsDtoList(newsService.getAllNews());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/news/{newsId}")
    public NewsDto getNews(@PathVariable Long newsId) throws Exception {
        return newsMapper.MapToNewsDto(newsService.findById(newsId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "news", consumes = APPLICATION_JSON_VALUE)
    public void createNews(@RequestBody NewsDto newsDto) throws Exception {
        newsService.saveNews(newsMapper.MapToNews(newsDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "news")
    public NewsDto updateNews(@RequestBody NewsDto newsDto) throws Exception {
        return newsMapper.MapToNewsDto(newsService.updateNews(newsMapper.MapToNews(newsDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/news/{newsId}")
    public void deleteNews(@PathVariable Long newsId) {
        newsService.deleteNews(newsId);
    }
}

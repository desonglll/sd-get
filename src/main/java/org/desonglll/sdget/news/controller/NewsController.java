package org.desonglll.sdget.news.controller;

import org.desonglll.sdget.common.result.Result;
import org.desonglll.sdget.news.dto.NewsRequestDto;
import org.desonglll.sdget.news.dto.NewsResponseDto;
import org.desonglll.sdget.news.entity.News;
import org.desonglll.sdget.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: mikeshinoda
 * @date: 2024/10/8
 * @description:
 */
@RestController
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    /**
     * Adds news to the system.
     *
     * @param newsDto the news data for creating a new news
     * @return ResponseEntity with a success message and created news details
     */
    @PostMapping
    public ResponseEntity<Result> addNews(@RequestBody NewsRequestDto newsDto) {
        News news = new News();
        news.setTitle(newsDto.getTitle());
        news.setContent(newsDto.getContent());
        news.setNewsColumn(newsDto.getNewsColumn());
        news.setCreatedTimestamp(OffsetDateTime.now());
        news.setUpdatedTimestamp(OffsetDateTime.now());
        News addedNews = newsService.addNews(news);
        Result result = Result.success("Add News successfully.", this.toResponseDto(addedNews));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Updates existing news by its ID.
     *
     * @param id      the ID of the news to update
     * @param newsDto the updated news data
     * @return ResponseEntity with a success message and updated news details
     */
    @PutMapping("/{id}")
    public ResponseEntity<Result> updateNews(@PathVariable Long id, @RequestBody NewsRequestDto newsDto) {
        News news = newsService.getNewsById(id);
        if (newsDto.getTitle() != null) {
            news.setTitle(newsDto.getTitle());
        }
        if (newsDto.getContent() != null) {
            news.setContent(newsDto.getContent());
        }
        if (newsDto.getNewsColumn() != null) {
            news.setNewsColumn(newsDto.getNewsColumn());
        }
        news.setUpdatedTimestamp(OffsetDateTime.now());
        News updatedNews = newsService.updateNews(news);
        Result result = Result.success("Update news with id " + id + " successfully.", this.toResponseDto(updatedNews));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Deletes news by its ID.
     *
     * @param id the ID of the news to delete
     * @return ResponseEntity with a success message and deleted news details
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteNews(@PathVariable Long id) {
        News deletedNews = newsService.deleteNews(id);
        Result result = Result.success("Deleted News with id " + id + " successfully.", this.toResponseDto(deletedNews));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Retrieves news by its ID.
     *
     * @param id the ID of the news to retrieve
     * @return ResponseEntity with a success message and the requested news details
     */
    @GetMapping("/{id}")
    public ResponseEntity<Result> getNewsById(@PathVariable Long id) {
        News news = newsService.getNewsById(id);
        return new ResponseEntity<>(Result.success("Get news " + id + " successfully.", news), HttpStatus.OK);
    }

    /**
     * Retrieves a list of all news.
     *
     * @return ResponseEntity with a success message and a list of all news
     */
    @GetMapping
    public ResponseEntity<Result> getAllNews() {
        List<News> allNews = newsService.getAllNewss();
        List<NewsResponseDto> responseDtoList = allNews.stream().map(this::toResponseDto).collect(Collectors.toList());
        Result result = Result.success("Get all news successfully.", responseDtoList);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Converts a News entity to NewsResponseDto for API responses.
     *
     * @param news the news entity to convert
     * @return the converted NewsResponseDto
     */
    private NewsResponseDto toResponseDto(News news) {
        NewsResponseDto newsResponseDto = new NewsResponseDto();
        newsResponseDto.setId(news.getId());
        newsResponseDto.setTitle(news.getTitle());
        newsResponseDto.setContent(news.getContent());
        newsResponseDto.setNewsColumn(news.getNewsColumn());
        newsResponseDto.setCreatedTimestamp(news.getCreatedTimestamp());
        newsResponseDto.setUpdatedTimestamp(news.getUpdatedTimestamp());
        return newsResponseDto;
    }
}

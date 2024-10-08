package org.desonglll.sdget.news.service;

import org.desonglll.sdget.common.exception.EmptyListException;
import org.desonglll.sdget.common.exception.NotFoundException;
import org.desonglll.sdget.news.entity.News;
import org.desonglll.sdget.news.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author: mikeshinoda
 * @date: 2024/10/8
 * @description:
 */
@Service
public class NewsService {

    private final NewsRepository newsRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<News> getAllNewss() {
        List<News> newsList = newsRepository.findAll();
        if (newsList.isEmpty()) {
            throw new EmptyListException("News list is empty");
        } else {
            return newsList;
        }
    }

    public News addNews(News news) {
        if (newsRepository.existsById(news.getId())) {
            throw new RuntimeException("News already exists");
        }
        return newsRepository.save(news);
    }

    public News getNewsById(Long id) {
        Optional<News> news = newsRepository.findById(id);
        if (news.isPresent()) {
            return news.get();
        } else {
            throw new NotFoundException("News not found to get with id: " + id);
        }
    }

    public News deleteNews(Long id) {
        if (newsRepository.existsById(id)) {
            News deletedNews = newsRepository.getById(id);
            newsRepository.deleteById(id);
            return deletedNews;
        } else {
            throw new NotFoundException("News not found to delete with id: " + id);
        }
    }

    public News updateNews(News news) {
        // 检查产品是否存在
        Optional<News> existingNews = newsRepository.findById(news.getId());

        if (existingNews.isPresent()) {
            // 如果产品存在，进行更新
            News updatedNews = existingNews.get();
            updatedNews.setTitle(news.getTitle());
            updatedNews.setContent(news.getContent());
            updatedNews.setNewsColumn(news.getNewsColumn());
            updatedNews.setUpdatedTimestamp(news.getUpdatedTimestamp());
            // 保存更新后的产品
            return newsRepository.save(updatedNews);
        } else {
            // 如果产品不存在，处理异常逻辑
            throw new NotFoundException("News not found to update with id: " + news.getId());
        }
    }

}

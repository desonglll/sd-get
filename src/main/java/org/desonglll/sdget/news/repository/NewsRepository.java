package org.desonglll.sdget.news.repository;

import org.desonglll.sdget.news.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: mikeshinoda
 * @date: 2024/10/8
 * @description:
 */
public interface NewsRepository extends JpaRepository<News, Long> {
}

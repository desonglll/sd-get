package org.desonglll.sdget.series.repository;

import org.desonglll.sdget.series.entity.Series;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: mikeshinoda
 * @date: 2024/9/30
 * @description:
 */
public interface SeriesRepository extends JpaRepository<Series, Long> {
}

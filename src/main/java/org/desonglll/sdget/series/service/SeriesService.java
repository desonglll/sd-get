package org.desonglll.sdget.series.service;

import org.desonglll.sdget.series.entity.Series;
import org.desonglll.sdget.series.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author: mikeshinoda
 * @date: 2024/9/30
 * @description:
 */
@Service
public class SeriesService {
    @Autowired
    private SeriesRepository seriesRepository;

    public List<Series> getAllSeries() {
        return seriesRepository.findAll();
    }

    public Series getSeriesById(Long id) {
        return seriesRepository.getById(id);
    }

    public Series addSeries(Series series) {
        return seriesRepository.save(series);
    }

    public void deleteSeries(Long id) {
        seriesRepository.deleteById(id);
    }

    public Series updateSeries(Series series) {
        Optional<Series> existingSeries = seriesRepository.findById(series.getId());
        if (existingSeries.isPresent()) {
            Series updatedSeries = existingSeries.get();
            updatedSeries.setName(series.getName());
            updatedSeries.setDescription(series.getDescription());
            updatedSeries.setUpdatedTimestamp(OffsetDateTime.now());
            return seriesRepository.save(updatedSeries);
        } else {
            throw new RuntimeException("Series not found with id " + series.getId());
        }
    }

}

package org.desonglll.sdget.series.service;

import org.desonglll.sdget.common.exception.EmptyListException;
import org.desonglll.sdget.common.exception.NotFoundException;
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
    private final SeriesRepository seriesRepository;

    @Autowired
    public SeriesService(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    public List<Series> getAllSeries() {
        List<Series> seriesList = seriesRepository.findAll();
        if (seriesList.isEmpty()) {
            throw new EmptyListException("Series list is empty");
        } else {
            return seriesList;
        }
    }

    public Series getSeriesById(Long id) {
        Optional<Series> series = seriesRepository.findById(id);
        if (series.isPresent()) {
            return series.get();
        } else {
            throw new NotFoundException("Series not found to get with id " + id);
        }
    }

    public Series addSeries(Series series) {
        return seriesRepository.save(series);
    }

    public Series deleteSeries(Long id) {

        if (seriesRepository.existsById(id)) {
            Series deletedSeries = seriesRepository.getById(id);
            seriesRepository.deleteById(id);
            return deletedSeries;
        } else {
            throw new NotFoundException("Series not found to delete with id " + id);
        }
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
            throw new NotFoundException("Series not found to update with id " + series.getId());
        }
    }

}

package org.desonglll.sdget.series.controller;

import org.desonglll.sdget.series.dto.SeriesRequestDto;
import org.desonglll.sdget.series.dto.SeriesResponseDto;
import org.desonglll.sdget.series.entity.Series;
import org.desonglll.sdget.series.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: mikeshinoda
 * @date: 2024/9/30
 * @description:
 */
@RestController
@RequestMapping("/series")
public class SeriesController {
    // 使用构造函数注入
    private final SeriesService seriesService;

    @Autowired
    public SeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    @GetMapping
    public ResponseEntity<List<SeriesResponseDto>> getAllSeries() {
        List<Series> series = seriesService.getAllSeries();
        List<SeriesResponseDto> responseDtoList = series.stream().map(this::toResponseDto).collect(Collectors.toList());
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeriesResponseDto> getSeriesById(@PathVariable Long id) {
        Series series = seriesService.getSeriesById(id);
        SeriesResponseDto seriesResponseDto = new SeriesResponseDto();
        seriesResponseDto.setId(series.getId());
        seriesResponseDto.setName(series.getName());
        seriesResponseDto.setDescription(series.getDescription());
        seriesResponseDto.setCreatedTimestamp(series.getCreatedTimestamp());
        seriesResponseDto.setUpdatedTimestamp(series.getUpdatedTimestamp());
        return new ResponseEntity<>(seriesResponseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SeriesResponseDto> addSeries(@RequestBody SeriesRequestDto seriesRequestDto) {
        Series series = new Series();
        series.setName(seriesRequestDto.getName());
        series.setDescription(seriesRequestDto.getDescription());
        series.setCreatedTimestamp(OffsetDateTime.now());
        series.setUpdatedTimestamp(OffsetDateTime.now());
        Series addedSeries = seriesService.addSeries(series);
        SeriesResponseDto seriesResponseDto = this.toResponseDto(addedSeries);
        return new ResponseEntity<>(seriesResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteSeries(@PathVariable Long id) {
        seriesService.deleteSeries(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeriesResponseDto> updateSeries(@PathVariable Long id, @RequestBody SeriesRequestDto seriesDto) {
        Series series = seriesService.getSeriesById(id);
        if (seriesDto.getName() != null) {
            series.setName(seriesDto.getName());
        }
        if (seriesDto.getDescription() != null) {
            series.setDescription(seriesDto.getDescription());
        }
        Series updatedSeries = seriesService.updateSeries(series);
        return new ResponseEntity<>(toResponseDto(updatedSeries), HttpStatus.OK);
    }

    private SeriesResponseDto toResponseDto(Series series) {
        SeriesResponseDto dto = new SeriesResponseDto();
        dto.setId(series.getId());
        dto.setName(series.getName());
        dto.setDescription(series.getDescription());
        dto.setCreatedTimestamp(series.getCreatedTimestamp());
        dto.setUpdatedTimestamp(series.getUpdatedTimestamp());
        return dto;
    }

}

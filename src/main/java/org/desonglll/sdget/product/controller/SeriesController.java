package org.desonglll.sdget.product.controller;

import org.desonglll.sdget.common.result.Result;
import org.desonglll.sdget.product.dto.SeriesRequestDto;
import org.desonglll.sdget.product.dto.SeriesResponseDto;
import org.desonglll.sdget.product.entity.Series;
import org.desonglll.sdget.product.service.SeriesService;
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
    public ResponseEntity<Result> getAllSeries() {
        List<Series> series = seriesService.getAllSeries();
        List<SeriesResponseDto> responseDtoList = series.stream().map(this::toResponseDto).collect(Collectors.toList());
        Result result = Result.success("Get all series successfully.", responseDtoList);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> getSeriesById(@PathVariable Long id) {
        Series series = seriesService.getSeriesById(id);
        SeriesResponseDto seriesResponseDto = this.toResponseDto(series);
        Result result = Result.success("Get series by id " + id + " successfully.", seriesResponseDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Result> addSeries(@RequestBody SeriesRequestDto seriesRequestDto) {
        Series series = new Series();
        series.setName(seriesRequestDto.getName());
        series.setDescription(seriesRequestDto.getDescription());
        series.setCreatedTimestamp(OffsetDateTime.now());
        series.setUpdatedTimestamp(OffsetDateTime.now());
        Series addedSeries = seriesService.addSeries(series);
        Result result = Result.success("Add series successfully", this.toResponseDto(addedSeries));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteSeries(@PathVariable Long id) {
        Series deletedSeries = seriesService.deleteSeries(id);
        SeriesResponseDto seriesResponseDto = this.toResponseDto(deletedSeries);
        Result result = Result.success("Delete Series with " + id + " successfully.", seriesResponseDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> updateSeries(@PathVariable Long id, @RequestBody SeriesRequestDto seriesDto) {
        Series series = seriesService.getSeriesById(id);
        if (seriesDto.getName() != null) {
            series.setName(seriesDto.getName());
        }
        if (seriesDto.getDescription() != null) {
            series.setDescription(seriesDto.getDescription());
        }
        Series updatedSeries = seriesService.updateSeries(series);
        Result result = Result.success("Update series with id: " + id + " successfully", toResponseDto(updatedSeries));
        return new ResponseEntity<>(result, HttpStatus.OK);
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

package org.desonglll.sdget.news.controller;

import org.desonglll.sdget.common.result.Result;
import org.desonglll.sdget.news.dto.ColumnRequestDto;
import org.desonglll.sdget.news.dto.ColumnResponseDto;
import org.desonglll.sdget.news.entity.Column;
import org.desonglll.sdget.news.service.ColumnService;
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
@RequestMapping("/column")
public class ColumnController {
    // 使用构造函数注入
    private final ColumnService columnService;

    @Autowired
    public ColumnController(ColumnService columnService) {
        this.columnService = columnService;
    }

    @GetMapping
    public ResponseEntity<Result> getAllColumn() {
        List<Column> column = columnService.getAllColumn();
        List<ColumnResponseDto> responseDtoList = column.stream().map(this::toResponseDto).collect(Collectors.toList());
        Result result = Result.success("Get all column successfully.", responseDtoList);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> getColumnById(@PathVariable Long id) {
        Column column = columnService.getColumnById(id);
        ColumnResponseDto columnResponseDto = this.toResponseDto(column);
        Result result = Result.success("Get column by id " + id + " successfully.", columnResponseDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Result> addColumn(@RequestBody ColumnRequestDto columnRequestDto) {
        Column column = new Column();
        column.setName(columnRequestDto.getName());
        column.setDescription(columnRequestDto.getDescription());
        column.setCreatedTimestamp(OffsetDateTime.now());
        column.setUpdatedTimestamp(OffsetDateTime.now());
        Column addedColumn = columnService.addColumn(column);
        Result result = Result.success("Add column successfully", this.toResponseDto(addedColumn));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteColumn(@PathVariable Long id) {
        Column deletedColumn = columnService.deleteColumn(id);
        ColumnResponseDto columnResponseDto = this.toResponseDto(deletedColumn);
        Result result = Result.success("Delete Column with " + id + " successfully.", columnResponseDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> updateColumn(@PathVariable Long id, @RequestBody ColumnRequestDto columnDto) {
        Column column = columnService.getColumnById(id);
        if (columnDto.getName() != null) {
            column.setName(columnDto.getName());
        }
        if (columnDto.getDescription() != null) {
            column.setDescription(columnDto.getDescription());
        }
        Column updatedColumn = columnService.updateColumn(column);
        Result result = Result.success("Update column with id: " + id + " successfully", toResponseDto(updatedColumn));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    private ColumnResponseDto toResponseDto(Column column) {
        ColumnResponseDto dto = new ColumnResponseDto();
        dto.setId(column.getId());
        dto.setName(column.getName());
        dto.setDescription(column.getDescription());
        dto.setCreatedTimestamp(column.getCreatedTimestamp());
        dto.setUpdatedTimestamp(column.getUpdatedTimestamp());
        return dto;
    }
}

package org.desonglll.sdget.news.service;

import org.desonglll.sdget.common.exception.EmptyListException;
import org.desonglll.sdget.common.exception.NotFoundException;
import org.desonglll.sdget.news.entity.Column;
import org.desonglll.sdget.news.repository.ColumnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;


/**
 * @author: mikeshinoda
 * @date: 2024/10/8
 * @description:
 */
@Service
public class ColumnService {
    private final ColumnRepository columnRepository;

    @Autowired
    public ColumnService(ColumnRepository columnRepository) {
        this.columnRepository = columnRepository;
    }

    public List<Column> getAllColumn() {
        List<Column> columnList = columnRepository.findAll();
        if (columnList.isEmpty()) {
            throw new EmptyListException("Column list is empty");
        } else {
            return columnList;
        }
    }

    public Column getColumnById(Long id) {
        Optional<Column> column = columnRepository.findById(id);
        if (column.isPresent()) {
            return column.get();
        } else {
            throw new NotFoundException("Column not found to get with id " + id);
        }
    }

    public Column addColumn(Column column) {
        return columnRepository.save(column);
    }

    public Column deleteColumn(Long id) {

        if (columnRepository.existsById(id)) {
            Column deletedColumn = columnRepository.getById(id);
            columnRepository.deleteById(id);
            return deletedColumn;
        } else {
            throw new NotFoundException("Column not found to delete with id " + id);
        }
    }

    public Column updateColumn(Column column) {
        Optional<Column> existingColumn = columnRepository.findById(column.getId());
        if (existingColumn.isPresent()) {
            Column updatedColumn = existingColumn.get();
            updatedColumn.setName(column.getName());
            updatedColumn.setDescription(column.getDescription());
            updatedColumn.setUpdatedTimestamp(OffsetDateTime.now());
            return columnRepository.save(updatedColumn);
        } else {
            throw new NotFoundException("Column not found to update with id " + column.getId());
        }
    }
}

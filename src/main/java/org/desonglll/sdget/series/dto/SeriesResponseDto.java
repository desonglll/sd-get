package org.desonglll.sdget.series.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

/**
 * @author: mikeshinoda
 * @date: 2024/9/30
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeriesResponseDto {
    private Long id;
    private String name;
    private String description;
    private OffsetDateTime createdTimestamp;
    private OffsetDateTime updatedTimestamp;
}

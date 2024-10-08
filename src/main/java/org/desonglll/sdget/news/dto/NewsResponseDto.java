package org.desonglll.sdget.news.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

/**
 * @author: mikeshinoda
 * @date: 2024/10/8
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String newsColumn;
    private OffsetDateTime createdTimestamp;
    private OffsetDateTime updatedTimestamp;
}

package org.desonglll.sdget.product.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.OffsetDateTime;

/**
 * @author: mikeshinoda
 * @date: 2024/9/30
 * @description:
 */
@Getter
@Setter
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private String imageLink;
    private OffsetDateTime createdTimestamp;
    private OffsetDateTime updatedTimestamp;
}

package org.desonglll.sdget.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: mikeshinoda
 * @date: 2024/9/30
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeriesRequestDto {
    private Long id;
    private String name;
    private String description;
}

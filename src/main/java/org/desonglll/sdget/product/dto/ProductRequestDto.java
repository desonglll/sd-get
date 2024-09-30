package org.desonglll.sdget.product.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: mikeshinoda
 * @date: 2024/9/29
 * @description:
 */
@Getter
@Setter
public class ProductRequestDto {
    private String name;
    private String description;
    private String imageLink;
}

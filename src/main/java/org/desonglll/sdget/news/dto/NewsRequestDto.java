package org.desonglll.sdget.news.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: mikeshinoda
 * @date: 2024/10/8
 * @description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class NewsRequestDto {
    private String title;
    private String content;
    private String newsColumn;
}

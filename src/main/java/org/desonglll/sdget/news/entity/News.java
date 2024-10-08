package org.desonglll.sdget.news.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.*;
import java.time.OffsetDateTime;

/**
 * @author: mikeshinoda
 * @date: 2024/10/8
 * @description:
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 自动生成主键
    private Long id;
    @javax.persistence.Column(name = "title")
    private String title;
    @javax.persistence.Column(name = "content")
    private String content;
    @javax.persistence.Column(name = "news_column")
    private String newsColumn;
    @javax.persistence.Column(name = "created_timestamp")
    private OffsetDateTime createdTimestamp;
    @Column(name = "updated_timestamp")
    private OffsetDateTime updatedTimestamp;
}

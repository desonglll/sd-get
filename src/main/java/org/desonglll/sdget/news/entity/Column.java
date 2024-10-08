package org.desonglll.sdget.news.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;

/**
 * @author: mikeshinoda
 * @date: 2024/10/8
 * @description:
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "news_column")
public class Column {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @javax.persistence.Column(name = "id")
    private Long id;
    @javax.persistence.Column(name = "name")
    private String name;
    @javax.persistence.Column(name = "description")
    private String description;
    @javax.persistence.Column(name = "created_timestamp")
    private OffsetDateTime createdTimestamp;
    @javax.persistence.Column(name = "updated_timestamp")
    private OffsetDateTime updatedTimestamp;
}


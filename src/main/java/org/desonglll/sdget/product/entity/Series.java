package org.desonglll.sdget.product.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;

/**
 * @author: mikeshinoda
 * @date: 2024/9/30
 * @description:
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "series")
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "created_timestamp")
    private OffsetDateTime createdTimestamp;
    @Column(name = "updated_timestamp")
    private OffsetDateTime updatedTimestamp;
}

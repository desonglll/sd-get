package org.desonglll.sdget.product.entity;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;

/**
 * @author: mikeshinoda
 * @date: 2024/9/29
 * @description:
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 自动生成主键
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "image_link")
    private String imageLink;
    @Column(name = "created_timestamp")
    private OffsetDateTime createdTimestamp;
    @Column(name = "updated_timestamp")
    private OffsetDateTime updatedTimestamp;
}

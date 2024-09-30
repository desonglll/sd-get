package org.desonglll.sdget.product.repository;


import org.desonglll.sdget.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: mikeshinoda
 * @date: 2024/9/29
 * @description:
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}

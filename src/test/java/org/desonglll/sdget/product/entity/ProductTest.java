package org.desonglll.sdget.product.entity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Description:
 * Author: mikeshinoda
 * Date: 2024/9/29
 */
public class ProductTest {

    @Test
    public void testProductNoArgsConstructor() {
        // 使用无参构造函数创建一个 Product 实例
        Product product = new Product();
        assertNotNull(product);

        // 测试默认值
        assertNull(product.getId());
        assertNull(product.getName());
        assertNull(product.getDescription());
    }

    @Test
    public void testProductAllArgsConstructor() {
        // 使用全参构造函数创建一个 Product 实例
        Product product = new Product(1L, "Product Name", "Product Description");

        // 验证属性是否被正确设置
        assertEquals(Long.valueOf(1L), product.getId());
        assertEquals("Product Name", product.getName());
        assertEquals("Product Description", product.getDescription());
    }

    @Test
    public void testSettersAndGetters() {
        // 测试 setter 和 getter 方法
        Product product = new Product();
        product.setId(2L);
        product.setName("New Product");
        product.setDescription("New Description");

        assertEquals(Long.valueOf(2L), product.getId());
        assertEquals("New Product", product.getName());
        assertEquals("New Description", product.getDescription());
    }
}

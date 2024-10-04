package org.desonglll.sdget.product.service;

import org.desonglll.sdget.common.exception.EmptyListException;
import org.desonglll.sdget.common.exception.NotFoundException;
import org.desonglll.sdget.product.entity.Product;
import org.desonglll.sdget.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author: mikeshinoda
 * @date: 2024/9/29
 * @description:
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        if (productList.isEmpty()) {
            throw new EmptyListException("Product list is empty");
        } else {
            return productList;
        }
    }

    public Product addProduct(Product product) {
        if (productRepository.existsById(product.getId())) {
            throw new RuntimeException("Product already exists");
        }
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new NotFoundException("Product not found to get with id: " + id);
        }
    }

    public Product deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            Product deletedProduct = productRepository.getById(id);
            productRepository.deleteById(id);
            return deletedProduct;
        } else {
            throw new NotFoundException("Product not found to delete with id: " + id);
        }
    }

    public Product updateProduct(Product product) {
        // 检查产品是否存在
        Optional<Product> existingProduct = productRepository.findById(product.getId());

        if (existingProduct.isPresent()) {
            // 如果产品存在，进行更新
            Product updatedProduct = existingProduct.get();
            updatedProduct.setName(product.getName());
            updatedProduct.setDescription(product.getDescription());
            updatedProduct.setUpdatedTimestamp(product.getUpdatedTimestamp());
            // 保存更新后的产品
            return productRepository.save(updatedProduct);
        } else {
            // 如果产品不存在，处理异常逻辑
            throw new NotFoundException("Product not found to update with id: " + product.getId());
        }
    }

}
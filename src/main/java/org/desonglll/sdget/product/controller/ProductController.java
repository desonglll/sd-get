package org.desonglll.sdget.product.controller;

import org.desonglll.sdget.common.result.Result;
import org.desonglll.sdget.product.dto.ProductRequestDto;
import org.desonglll.sdget.product.dto.ProductResponseDto;
import org.desonglll.sdget.product.entity.Product;
import org.desonglll.sdget.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mikeshinoda
 * @date 2024/9/29
 * @description This controller provides RESTful API endpoints for managing products.
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Adds a new product to the system.
     *
     * @param productDto the product data for creating a new product
     * @return ResponseEntity with a success message and created product details
     */
    @PostMapping
    public ResponseEntity<Result> addProduct(@RequestBody ProductRequestDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setCreatedTimestamp(OffsetDateTime.now());
        product.setUpdatedTimestamp(OffsetDateTime.now());
        Product addedProduct = productService.addProduct(product);
        Result result = Result.success("Add Product successfully.", this.toResponseDto(addedProduct));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Updates an existing product by its ID.
     *
     * @param id         the ID of the product to update
     * @param productDto the updated product data
     * @return ResponseEntity with a success message and updated product details
     */
    @PutMapping("/{id}")
    public ResponseEntity<Result> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto productDto) {
        Product product = productService.getProductById(id);
        if (productDto.getName() != null) {
            product.setName(productDto.getName());
        }
        if (productDto.getDescription() != null) {
            product.setDescription(productDto.getDescription());
        }
        product.setUpdatedTimestamp(OffsetDateTime.now());
        Product updatedProduct = productService.updateProduct(product);
        Result result = Result.success("Update product with id " + id + " successfully.", this.toResponseDto(updatedProduct));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete
     * @return ResponseEntity with a success message and deleted product details
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteProduct(@PathVariable Long id) {
        Product deletedProduct = productService.deleteProduct(id);
        Result result = Result.success("Deleted Product with id " + id + " successfully.", this.toResponseDto(deletedProduct));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id the ID of the product to retrieve
     * @return ResponseEntity with a success message and the requested product details
     */
    @GetMapping("/{id}")
    public ResponseEntity<Result> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(Result.success("Get product " + id + " successfully.", product), HttpStatus.OK);
    }

    /**
     * Retrieves a list of all products.
     *
     * @return ResponseEntity with a success message and a list of all products
     */
    @GetMapping
    public ResponseEntity<Result> getAllProducts() {
        List<Product> allProducts = productService.getAllProducts();
        List<ProductResponseDto> responseDtoList = allProducts.stream().map(this::toResponseDto).collect(Collectors.toList());
        Result result = Result.success("Get all products successfully.", responseDtoList);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Converts a Product entity to ProductResponseDto for API responses.
     *
     * @param product the product entity to convert
     * @return the converted ProductResponseDto
     */
    private ProductResponseDto toResponseDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setImageLink(product.getImageLink());
        productResponseDto.setCreatedTimestamp(product.getCreatedTimestamp());
        productResponseDto.setUpdatedTimestamp(product.getUpdatedTimestamp());
        return productResponseDto;
    }
}

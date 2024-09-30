package org.desonglll.sdget.product.controller;

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
 * @author: mikeshinoda
 * @date: 2024/9/29
 * @description:
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody ProductRequestDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setCreatedTimestamp(OffsetDateTime.now());
        product.setUpdatedTimestamp(OffsetDateTime.now());
        Product addedProduct = productService.addProduct(product);
        return new ResponseEntity<>(toResponseDto(addedProduct), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto productDto) {
        Product product = productService.getProductById(id);
        if (productDto.getName() != null) {
            product.setName(productDto.getName());
        }
        if (productDto.getDescription() != null) {
            product.setDescription(productDto.getDescription());
        }
        product.setUpdatedTimestamp(OffsetDateTime.now());
        Product updatedProduct = productService.updateProduct(product);
        return new ResponseEntity<>(toResponseDto(updatedProduct), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(toResponseDto(product), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<Product> allProducts = productService.getAllProducts();
        List<ProductResponseDto> responseDtoList = allProducts.stream().map(this::toResponseDto).collect(Collectors.toList());
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

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
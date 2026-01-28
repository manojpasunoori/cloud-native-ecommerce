package com.manoj.ecommerce.product.controller;

import com.manoj.ecommerce.product.model.Product;
import com.manoj.ecommerce.product.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 1. Create a Product
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // 2. Get All Products
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
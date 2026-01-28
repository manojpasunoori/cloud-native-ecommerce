package com.manoj.ecommerce.product.repository;

import com.manoj.ecommerce.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// This interface gives us methods like .save(), .findAll(), .findById() for free!
public interface ProductRepository extends JpaRepository<Product, Long> {
}
package com.example.ecommerce.repository; // Change to match your exact package structure

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
package com.example.ecommerce.order.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.model.Product;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final List<Product> cart = new ArrayList<>();

    // 1. Add a product to the cart
    @PostMapping("/add")
    public String addToCart(@RequestBody Product product) {
        cart.add(product);
        return product.getName() + " added to your cart successfully!";
    }

    // 2. View all items currently in the cart
    @GetMapping("/cart")
    public List<Product> viewCart() {
        return cart;
    }
}
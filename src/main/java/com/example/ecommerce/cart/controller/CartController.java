package com.example.ecommerce.cart.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.cart.model.CartItem;
import com.example.ecommerce.cart.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity<CartItem> addToCart(@RequestBody CartItem item) {
        return ResponseEntity.ok(cartService.addToCart(item));
    }

    @GetMapping("/view/{userId}")
    public ResponseEntity<List<CartItem>> viewCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartByUser(userId));
    }
}
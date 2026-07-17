package com.example.ecommerce.cart.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce.cart.model.CartItem;
import com.example.ecommerce.cart.repository.CartRepository;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public CartItem addToCart(CartItem item) {
        return cartRepository.save(item);
    }

    public List<CartItem> getCartByUser(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    @Transactional
    public void clearCart(Long userId) {
        cartRepository.deleteByUserId(userId);
    }
}
package com.example.ecommerce.order.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ecommerce.cart.model.CartItem;
import com.example.ecommerce.cart.service.CartService;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.order.model.Order;
import com.example.ecommerce.order.repository.OrderRepository;
import com.example.ecommerce.repository.ProductRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, CartService cartService, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.productRepository = productRepository;
    }

    public Order checkoutOrder(Long userId) {
        List<CartItem> cartItems = cartService.getCartByUser(userId);
        if(cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty!");
        }

        double total = 0.0;
        for(CartItem item : cartItems) {
            Product product = productRepository.findById(item.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
            total += product.getPrice() * item.getQuantity();
        }

        Order order = new Order();
        order.setUserId(userId);
        order.setTotalAmount(total);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PLACED");

        Order savedOrder = orderRepository.save(order);
        cartService.clearCart(userId); // Clear cart items after placing the order
        
        return savedOrder;
    }

    public List<Order> getUserOrderHistory(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
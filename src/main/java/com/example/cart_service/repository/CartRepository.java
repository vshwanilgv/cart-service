package com.example.cart_service.repository;

import com.example.cart_service.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByProductId(Long productId);
}
package com.example.cart_service.service;

import com.example.cart_service.dto.CartDTO;
import com.example.cart_service.dto.CartItemDTO;

import java.util.List;

public interface CartService {
    CartDTO getCartByUserId();

    CartDTO addProductToCart(Long userId, CartItemDTO cartItemDTO);

    CartDTO updateProductInCart(Long userId, Long productId, CartItemDTO cartItemDTO);

    void removeProductFromCart(Long userId, Long productId);
}
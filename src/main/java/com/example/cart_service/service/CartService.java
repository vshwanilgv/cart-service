package com.example.cart_service.service;

import com.example.cart_service.dto.CartDTO;

public interface CartService {
    CartDTO getCartByUserId(Long userId);

    CartDTO addProductToCart(Long userId, CartItemDTO cartItemDTO);

    CartDTO updateProductInCart(Long userId, Long productId, CartItemDTO cartItemDTO);

    void removeProductFromCart(Long userId, Long productId);
}
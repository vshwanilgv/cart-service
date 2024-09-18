package com.example.cart_service.service;

import com.example.cart_service.model.CartItem;

import java.util.List;

public interface CartService {
    List<CartItem> getCartItems();

    CartItem addCartItem(CartItemDTO cartItemDTO);

    CartItem updateCartItem(Long id, CartItemDTO cartItemDTO);

    void removeCartItem(Long id);
}
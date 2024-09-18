package com.example.cart_service.exception;

public class CartItemNotFoundException extends RuntimeException {
    public CartItemNotFoundException(Long productId) {
        super("Product with ID " + productId + " not found in cart");
    }
}
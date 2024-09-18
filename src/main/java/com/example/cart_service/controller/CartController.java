package com.example.cart_service.controller;


import com.example.cart_service.model.CartItem;
import com.example.cart_service.service.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController extends AbstractController {
    @Autowired
    private CartServiceImpl cartService;

    @GetMapping
    public ResponseEntity<List<CartItem>> getCartItems() {
        List<CartItem> cartItems = cartService.getCartItems();
        return successResponse(cartItems, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CartItem> addCartItem(@RequestBody CartItemDTO cartItemDTO) {
        return ResponseEntity.ok(cartService.addCartItem(cartItemDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable Long id, @RequestBody CartItemDTO cartItemDTO) {
        return ResponseEntity.ok(cartService.updateCartItem(id, cartItemDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeCartItem(@PathVariable Long id) {
        cartService.removeCartItem(id);
        return ResponseEntity.noContent().build();
    }
}

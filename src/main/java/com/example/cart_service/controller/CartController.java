package com.example.cart_service.controller;


import com.example.cart_service.dto.CartDTO;
import com.example.cart_service.service.CartService;
import com.example.cart_service.service.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController extends AbstractController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartDTO>> getAllCarts() {
        List<CartDTO> carts = cartService.getAllCarts();
        return successResponse(carts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDTO> getCartById(@PathVariable Long id) {
        CartDTO cart = cartService.getCartById(id);
        return successResponse(cart, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CartDTO> createCart(@RequestBody CartDTO cartDTO) {
        CartDTO createdCart = cartService.createCart(cartDTO);
        return createdResponse(createdCart);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartDTO> updateCart(@PathVariable Long id, @RequestBody CartDTO cartDTO) {
        CartDTO updatedCart = cartService.updateCart(id, cartDTO);
        return successResponse(updatedCart, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return noContentResponse();
    }
}

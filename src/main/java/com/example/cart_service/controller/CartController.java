package com.example.cart_service.controller;


import com.example.cart_service.dto.CartDTO;
import com.example.cart_service.service.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController extends AbstractController {
    @Autowired
    private CartServiceImpl cartService;

    @GetMapping
    public ResponseEntity<CartDTO> getCartByUserId(@RequestParam Long userId) {
        CartDTO cart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping
    public ResponseEntity<CartDTO> addProductToCart(@RequestParam Long userId, @RequestBody CartItemDTO cartItemDTO) {
        CartDTO updatedCart = cartService.addProductToCart(userId, cartItemDTO);
        return ResponseEntity.ok(updatedCart);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<CartDTO> updateProductInCart(@RequestParam Long userId, @PathVariable Long productId, @RequestBody CartItemDTO cartItemDTO) {
        CartDTO updatedCart = cartService.updateProductInCart(userId, productId, cartItemDTO);
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> removeProductFromCart(@RequestParam Long userId, @PathVariable Long productId) {
        cartService.removeProductFromCart(userId, productId);
        return ResponseEntity.noContent().build();
    }
}

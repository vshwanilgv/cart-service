package com.example.cart_service.service;

import com.example.cart_service.exception.CartItemNotFoundException;
import com.example.cart_service.model.CartItem;
import com.example.cart_service.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<CartItem> getCartItems() {
        return cartRepository.findAll();
    }

    @Override
    public CartItem addCartItem(CartItemDTO cartItemDTO) {
        CartItem cartItem = new CartItem();
        cartItem.setProductId(cartItemDTO.getProductId());
        cartItem.setQuantity(cartItemDTO.getQuantity());
        cartItem.setPrice(cartItemDTO.getPrice());
        return cartRepository.save(cartItem);
    }

    @Override
    public CartItem updateCartItem(Long id, CartItemDTO cartItemDTO) {
        CartItem cartItem = cartRepository.findById(id)
                .orElseThrow(() -> new CartItemNotFoundException("Cart item not found with id: " + id));
        cartItem.setQuantity(cartItemDTO.getQuantity());
        cartItem.setPrice(cartItemDTO.getPrice());
        return cartRepository.save(cartItem);
    }

    @Override
    public void removeCartItem(Long id) {
        CartItem cartItem = cartRepository.findById(id)
                .orElseThrow(() -> new CartItemNotFoundException("Cart item not found with id: " + id));
        cartRepository.delete(cartItem);
    }


}

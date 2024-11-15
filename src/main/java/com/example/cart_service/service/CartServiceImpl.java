package com.example.cart_service.service;

import com.example.cart_service.exception.CartNotFoundException;
import com.example.cart_service.dto.CartDTO;
import com.example.cart_service.model.Cart;
import com.example.cart_service.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CartDTO> getAllCarts() {
        List<Cart> carts = cartRepository.findAll();
        return carts.stream()
                .map(cart -> modelMapper.map(cart, CartDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CartDTO getCartById(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException(id));
        return modelMapper.map(cart, CartDTO.class);
    }

    @Override
    public CartDTO createCart(CartDTO cartDTO) {
        Cart cart = modelMapper.map(cartDTO, Cart.class);
        Cart savedCart = cartRepository.save(cart);
        return modelMapper.map(savedCart, CartDTO.class);
    }

    @Override
    public CartDTO updateCart(Long id, CartDTO cartDTO) {
        Cart existingCart = cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException(id));
        modelMapper.map(cartDTO, existingCart);
        Cart updatedCart = cartRepository.save(existingCart);
        return modelMapper.map(updatedCart, CartDTO.class);
    }

    @Override
    public void deleteCart(Long id) {
        if (!cartRepository.existsById(id)) {
            throw new CartNotFoundException(id);
        }
        cartRepository.deleteById(id);
    }


}

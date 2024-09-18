package com.example.cart_service.service;

import com.example.cart_service.exception.CartItemNotFoundException;
import com.example.cart_service.dto.CartDTO;
import com.example.cart_service.model.Cart;
import com.example.cart_service.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ModelMapper modelMapper;

    private static final Logger logger = LogManager.getLogger(CartServiceImpl.class);

    @Override
    public CartDTO getCartByUserId(Long userId) {
        try {
            logger.info("Fetching cart for user ID: {}", userId);
            Cart cart = cartRepository.findByUserId(userId)
                    .orElseThrow(() -> new RuntimeException("Cart not found for user ID: " + userId));
            return modelMapper.map(cart, CartDTO.class);
        } catch (Exception e) {
            logger.error("Failed to fetch cart for user ID: " + userId, e);
            throw new RuntimeException("Failed to fetch cart for user ID: " + userId, e);
        }
    }

    @Override
    public CartDTO addProductToCart(Long userId, CartItemDTO cartItemDTO) {
        try {
            logger.info("Adding product to cart for user ID: {}", userId);
            Cart cart = cartRepository.findByUserId(userId).orElse(new Cart());
            CartItem cartItem = modelMapper.map(cartItemDTO, CartItem.class);
            cart.getItems().add(cartItem);
            cart.setUserId(userId);
            Cart savedCart = cartRepository.save(cart);
            return modelMapper.map(savedCart, CartDTO.class);
        } catch (Exception e) {
            logger.error("Failed to add product to cart for user ID: " + userId, e);
            throw new RuntimeException("Failed to add product to cart", e);
        }
    }

    @Override
    public CartDTO updateProductInCart(Long userId, Long productId, CartItemDTO cartItemDTO) {
        try {
            logger.info("Updating product in cart for user ID: {} and product ID: {}", userId, productId);
            Cart cart = cartRepository.findByUserId(userId)
                    .orElseThrow(() -> new RuntimeException("Cart not found for user ID: " + userId));
            CartItem item = cart.getItems().stream()
                    .filter(i -> i.getProductId().equals(productId))
                    .findFirst()
                    .orElseThrow(() -> new CartItemNotFoundException(productId));

            item.setQuantity(cartItemDTO.getQuantity());
            item.setPrice(cartItemDTO.getPrice());
            Cart updatedCart = cartRepository.save(cart);
            return modelMapper.map(updatedCart, CartDTO.class);
        } catch (Exception e) {
            logger.error("Failed to update product in cart for user ID: " + userId + " and product ID: " + productId, e);
            throw new RuntimeException("Failed to update product in cart", e);
        }
    }

    @Override
    public void removeProductFromCart(Long userId, Long productId) {
        try {
            logger.info("Removing product with ID: {} from cart for user ID: {}", productId, userId);

            Cart cart = cartRepository.findByUserId(userId)
                    .orElseThrow(() -> new RuntimeException("Cart not found for user ID: " + userId));

            boolean removed = cart.getItems().removeIf(item -> item.getProductId().equals(productId));

            if (!removed) {
                throw new CartItemNotFoundException(productId);
            }

            cartRepository.save(cart);
        } catch (Exception e) {
            logger.error("Failed to remove product with ID: " + productId + " from cart for user ID: " + userId, e);
            throw new RuntimeException("Failed to remove product from cart", e);
        }
    }


}

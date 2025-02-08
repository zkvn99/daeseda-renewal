package com.experiment.daeseda_renewal.service.cart;

import com.experiment.daeseda_renewal.dto.CartItemDto;

import java.util.List;

public interface CartService {

    void addToCart(Long userId, Long productId, int quantity);
    List<CartItemDto> getCartItems(Long userId);
    public void clearCart(Long userId);
}

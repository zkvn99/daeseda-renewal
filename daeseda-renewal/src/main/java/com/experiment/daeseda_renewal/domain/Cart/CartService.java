package com.experiment.daeseda_renewal.domain.Cart;

import java.util.List;

public interface CartService {

    void addToCart(Long userId, Long productId, int quantity);
    List<CartItemDto> getCartItems(Long userId);
    public void clearCart(Long userId);
}

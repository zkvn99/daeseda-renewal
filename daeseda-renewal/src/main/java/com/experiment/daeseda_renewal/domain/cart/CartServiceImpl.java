package com.experiment.daeseda_renewal.domain.cart;

import com.experiment.daeseda_renewal.domain.product.Product;
import com.experiment.daeseda_renewal.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    @Override
    public void addToCart(Long userId, Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

        CartItem cartItem = CartItem.builder()
                .userId(userId)
                .product(product)
                .quantity(quantity)
                .build();

        cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItemDto> getCartItems(Long userId) {
        // userId로 해당 사용자의 장바구니 아이템 목록을 가져옵니다.
        List<CartItem> cartItems = cartItemRepository.findByUserId(userId);

        // CartItem 객체들을 CartItemDto 객체로 변환하여 반환합니다.
        return cartItems.stream()
                .map(cartItem -> CartItemDto.builder()
                        .productId(cartItem.getProduct().getId())
                        .name(cartItem.getProduct().getName())
                        .quantity(cartItem.getQuantity())
                        .price(cartItem.getProduct().getPrice()) // 가격 가져오기
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void clearCart(Long userId) {
        List<CartItem> cartItems = cartItemRepository.findByUserId(userId);

        if (!cartItems.isEmpty()) {
            cartItemRepository.deleteAll(cartItems);
        }
    }
}

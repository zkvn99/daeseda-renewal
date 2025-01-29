package com.experiment.daeseda_renewal.service.order;

import com.experiment.daeseda_renewal.dto.CartItemDto;
import com.experiment.daeseda_renewal.dto.OrderDto;
import com.experiment.daeseda_renewal.dto.OrderItemDto;
import com.experiment.daeseda_renewal.entity.Order;
import com.experiment.daeseda_renewal.entity.OrderItem;
import com.experiment.daeseda_renewal.entity.Product;
import com.experiment.daeseda_renewal.repository.OrderItemRepository;
import com.experiment.daeseda_renewal.repository.OrderRepository;
import com.experiment.daeseda_renewal.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartService cartService;

    @Override
    public void requestOrder() {

    }

    @Override
    public OrderDto placeOrder(Long userId) {
        List<CartItemDto> cartItems = cartService.getCartItems(userId);
        if (cartItems.isEmpty()) {
            throw new IllegalStateException("장바구니가 비어 있습니다.");
        }

        Order order = Order.builder()
                .userId(userId)
                .orderDate(LocalDateTime.now())
                .build();

        for (CartItemDto cartItem : cartItems) {
            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(Product.builder().id(cartItem.getProductId()).build())
                    .quantity(cartItem.getQuantity())
                    .price(cartItem.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                    .build();

            order.addOrderItem(orderItem);
        }

        orderRepository.save(order);
        cartService.clearCart(userId);

        return OrderDto.builder()
                .orderId(order.getId())
                .userId(order.getUserId())
                .orderDate(order.getOrderDate())
                .orderItems(cartItems.stream()
                        .map(cartItem -> OrderItemDto.builder()
                                .productId(cartItem.getProductId())
                                .name(cartItem.getName())
                                .quantity(cartItem.getQuantity())
                                .totalPrice(cartItem.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}

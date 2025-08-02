package com.experiment.daeseda_renewal.domain.order;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);

    OrderDto getOrderByOrderId(Long orderId);

    void deleteOrder(Long orderId);

    OrderDto updateOrderByOrderId(Long orderId, OrderDto orderDto);
}

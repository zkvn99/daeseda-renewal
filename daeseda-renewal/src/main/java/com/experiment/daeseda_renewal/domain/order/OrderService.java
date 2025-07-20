package com.experiment.daeseda_renewal.domain.order;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);
    OrderDto getOrderById(Long orderId);
}

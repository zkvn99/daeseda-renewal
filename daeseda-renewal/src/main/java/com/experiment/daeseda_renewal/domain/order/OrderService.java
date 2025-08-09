package com.experiment.daeseda_renewal.domain.order;

import com.experiment.daeseda_renewal.domain.order.dto.CreateOrderRequest;

public interface OrderService {
    void createOrder(CreateOrderRequest request);

    OrderDto getOrderByOrderId(Long orderId);

    void deleteOrder(Long orderId);

    OrderDto updateOrderByOrderId(Long orderId, OrderDto orderDto);
}

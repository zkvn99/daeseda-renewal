package com.experiment.daeseda_renewal.domain.order;

import com.experiment.daeseda_renewal.domain.order.dto.CreateOrderRequest;
import com.experiment.daeseda_renewal.domain.order.dto.OrderResponse;

import java.util.List;

public interface OrderService {
    void createOrder(CreateOrderRequest request);

    List<OrderResponse> getMyOrderList(Long userId);

    void cancelOrder(Long orderId);

    OrderDto updateOrderByOrderId(Long orderId, OrderDto orderDto);
}

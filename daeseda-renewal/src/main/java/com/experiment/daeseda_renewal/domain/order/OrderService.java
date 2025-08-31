package com.experiment.daeseda_renewal.domain.order;

import com.experiment.daeseda_renewal.domain.order.dto.CreateOrderRequest;
import com.experiment.daeseda_renewal.domain.order.dto.OrderResponse;
import com.experiment.daeseda_renewal.domain.order.dto.UpdateOrderRequest;
import java.util.List;

public interface OrderService {

    void createOrder(CreateOrderRequest request);

    List<OrderResponse> getMyOrderList(Long userId);

    void cancelOrder(Long orderId);

    void cashOrder(Long orderId);

    UpdateOrderRequest updateOrderByOrderId(Long orderId, UpdateOrderRequest orderDto);
}

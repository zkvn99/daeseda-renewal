package com.experiment.daeseda_renewal.domain.Order;

public interface OrderService {

    void requestOrder();
    OrderDto placeOrder(Long userId);
}

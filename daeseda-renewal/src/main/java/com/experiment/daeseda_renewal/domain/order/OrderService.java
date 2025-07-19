package com.experiment.daeseda_renewal.domain.order;

public interface OrderService {

    void requestOrder();
    OrderDto placeOrder(Long userId);
}

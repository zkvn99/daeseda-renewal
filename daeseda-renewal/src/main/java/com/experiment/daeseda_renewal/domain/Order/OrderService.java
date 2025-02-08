package com.experiment.daeseda_renewal.service.order;

import com.experiment.daeseda_renewal.dto.OrderDto;

public interface OrderService {

    void requestOrder();
    OrderDto placeOrder(Long userId);
}

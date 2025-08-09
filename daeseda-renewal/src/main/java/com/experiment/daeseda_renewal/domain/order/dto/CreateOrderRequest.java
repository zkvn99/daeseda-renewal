package com.experiment.daeseda_renewal.domain.order.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
public class CreateOrderRequest {
    private final Long userId;
    private final Long addressId;
    private final LocalDate deliveryDate;
    private final String orderStatus;
    private final String washingMethod;
    private final BigDecimal totalPrice;
}

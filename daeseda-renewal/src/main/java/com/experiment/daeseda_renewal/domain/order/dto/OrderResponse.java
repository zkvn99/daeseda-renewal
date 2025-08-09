package com.experiment.daeseda_renewal.domain.order.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class OrderResponse {
    private final Long orderId;
    private final LocalDateTime regTime;
    private final LocalDateTime modTime;
    private final LocalDate deliveryDate;
    private final LocalDateTime pickupDate;
    private final String orderStatus;
    private final BigDecimal totalPrice;
    private final String washingMethod;
    private final Long userId;
    private final Long addressId;
}

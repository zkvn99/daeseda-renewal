package com.experiment.daeseda_renewal.domain.order.dto;

import com.experiment.daeseda_renewal.constant.OrderStatus;
import com.experiment.daeseda_renewal.constant.WashingMethod;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class OrderResponse {
    private Long orderId;
    private String userName;
    private String address;
    private LocalDate deliveryDate;
    private LocalDateTime pickupDate;
    private OrderStatus orderStatus;
    private BigDecimal totalPrice;
    private WashingMethod washingMethod;
}

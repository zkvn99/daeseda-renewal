package com.experiment.daeseda_renewal.domain.Order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class OrderDto {
    private Long orderId;
    private Long userId;
    private LocalDateTime orderDate;
    private List<OrderItemDto> orderItems;
}

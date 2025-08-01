package com.experiment.daeseda_renewal.domain.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@Builder
public class OrderItemDto {
    private Long productId;
    private String name;
    private int quantity;
    private BigDecimal totalPrice;//바꿨어.
}

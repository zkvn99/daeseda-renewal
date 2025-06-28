package com.experiment.daeseda_renewal.domain.Cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@Builder
public class CartItemDto {
    private Long productId;
    private String name;
    private BigDecimal price;
    private int quantity;
}

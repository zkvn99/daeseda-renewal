package com.experiment.daeseda_renewal.domain.order.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CancelOrderRequest {
    private Long orderId;
}

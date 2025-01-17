package com.experiment.daeseda_renewal.dto;

import com.experiment.daeseda_renewal.entity.Order;
import com.experiment.daeseda_renewal.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OrderDTO {

    public static OrderDTO fromOrder(Order order) {
        if(order == null)
            return null;

        return OrderDTO.builder()
                .build();
    }
}

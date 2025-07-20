package com.experiment.daeseda_renewal.service;

import com.experiment.daeseda_renewal.constant.OrderStatus;
import com.experiment.daeseda_renewal.domain.order.OrderDto;
import com.experiment.daeseda_renewal.domain.order.OrderService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
@Transactional
public class OrderServiceTest {
    @Autowired
    OrderService orderService;

    @Test
    void createOrderSuccess() {
        //줄게
        OrderDto orderDto = OrderDto.builder()
                .deliveryDate(LocalDate.now().plusDays(1))
                .pickupDate(LocalDateTime.now().plusHours(3))
                .orderStatus(OrderStatus.CASH)
                .totalPrice(new BigDecimal("100.00"))
                .washingMethod("드라크리닝")
                .build();
        //언제
        OrderDto orderDtoResult = orderService.createOrder(orderDto);
        //그래서
        assertThat(orderDtoResult).isNotNull();
        assertThat(orderDtoResult.getOrderId()).isNotNull();
        assertThat(orderDtoResult.getDeliveryDate()).isNotNull();
        assertThat(orderDtoResult.getPickupDate()).isNotNull();
        assertThat(orderDtoResult.getOrderStatus()).isEqualTo(OrderStatus.CASH);
        assertThat(orderDtoResult.getTotalPrice()).isEqualTo(new BigDecimal("100.00"));
        assertThat(orderDtoResult.getWashingMethod()).isEqualTo("드라크리닝");


    }
}

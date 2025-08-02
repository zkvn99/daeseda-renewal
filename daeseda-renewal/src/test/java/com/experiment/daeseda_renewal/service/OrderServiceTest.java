package com.experiment.daeseda_renewal.service;

import com.experiment.daeseda_renewal.constant.OrderStatus;
import com.experiment.daeseda_renewal.domain.order.OrderDto;
import com.experiment.daeseda_renewal.domain.order.OrderRepository;
import com.experiment.daeseda_renewal.domain.order.OrderService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class OrderServiceTest {
    @Autowired
    OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    @DisplayName("주문 생성")
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

    @Test
    @DisplayName("주문 조회")
    void getOrderSuccess() {
        //given
        OrderDto testSaveOrder = OrderDto.builder()
                .deliveryDate(LocalDate.now().plusDays(1))
                .pickupDate(LocalDateTime.now().plusHours(3))
                .orderStatus(OrderStatus.CASH)
                .totalPrice(new BigDecimal("100.00"))
                .washingMethod("드라크리닝")
                .build();

        OrderDto savedOrder = orderService.createOrder(testSaveOrder);
        //order 저장
        Long orderId = savedOrder.getOrderId();
        //orderId만 가져오기

        //when
        OrderDto findByOrderId = orderService.getOrderByOrderId(orderId);
        //then
        assertThat(findByOrderId).isNotNull();
        assertThat(findByOrderId.getOrderId()).isEqualTo(orderId);
        assertThat(findByOrderId.getTotalPrice()).isEqualTo(testSaveOrder.getTotalPrice());
        assertThat(findByOrderId.getWashingMethod()).isEqualTo(testSaveOrder.getWashingMethod());
        assertThat(findByOrderId.getOrderStatus()).isEqualTo(testSaveOrder.getOrderStatus());
    }

    @Test
    @DisplayName("주문 삭제")
    void deleteOrderSuccess() {
        //given
        OrderDto testSaveOrder = OrderDto.builder()
                .deliveryDate(LocalDate.now().plusDays(1))
                .pickupDate(LocalDateTime.now().plusHours(3))
                .orderStatus(OrderStatus.CASH)
                .totalPrice(new BigDecimal("100.00"))
                .washingMethod("드라크리닝")
                .build();

        OrderDto savedOrder = orderService.createOrder(testSaveOrder);
        Long orderId = savedOrder.getOrderId();

        //when
        orderService.deleteOrder(orderId);

        //then
        assertTrue(orderRepository.findByOrderId(orderId).isEmpty());
    }

    @Test
    @DisplayName("주문 수정")
    void updateOrderSuccess() {
        //given 주문 생성
        OrderDto originalOrder = OrderDto.builder()
                .deliveryDate(LocalDate.now().plusDays(1))
                .pickupDate(LocalDateTime.now().plusHours(3))
                .orderStatus(OrderStatus.CASH)
                .totalPrice(new BigDecimal("100.00"))
                .washingMethod("드라크리닝")
                .build();

        OrderDto savedOrder = orderService.createOrder(originalOrder);
        Long orderId = savedOrder.getOrderId();
        OrderDto updateDto = OrderDto.builder()
                .regTime(savedOrder.getRegTime())
                .modTime(LocalDateTime.now())
                .deliveryDate(LocalDate.now().plusDays(5))
                .pickupDate(LocalDateTime.now().plusDays(4).withHour(10).withMinute(30))
                .orderStatus(OrderStatus.COMPLETE)
                .totalPrice(new BigDecimal("150.00"))
                .washingMethod("일반세탁")
                .build();

        //when
        OrderDto updatedOrder = orderService.updateOrderByOrderId(orderId, updateDto);

        //then
        assertThat(updatedOrder).isNotNull();
        assertThat(updatedOrder.getOrderId()).isEqualTo(orderId);
        assertThat(updatedOrder.getDeliveryDate()).isEqualTo(updateDto.getDeliveryDate());
        assertThat(updatedOrder.getPickupDate()).isEqualTo(updateDto.getPickupDate());
        assertThat(updatedOrder.getOrderStatus()).isEqualTo(updateDto.getOrderStatus());
        assertThat(updatedOrder.getTotalPrice()).isEqualTo(updateDto.getTotalPrice());
        assertThat(updatedOrder.getWashingMethod()).isEqualTo(updateDto.getWashingMethod());
        assertThat(updatedOrder.getModTime()).isEqualTo(updateDto.getModTime());
    }
}

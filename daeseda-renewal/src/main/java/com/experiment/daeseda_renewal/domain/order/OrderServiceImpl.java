package com.experiment.daeseda_renewal.domain.order;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor

public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = Order.builder()
                .regTime(LocalDateTime.now())
                .modTime(null)
                .deliveryDate(orderDto.getDeliveryDate())
                .pickupDate(orderDto.getPickupDate())
                .orderStatus(orderDto.getOrderStatus())
                .totalPrice(orderDto.getTotalPrice())
                .washingMethod(orderDto.getWashingMethod())
                .build();

        Order savedOrder = orderRepository.save(order);

        return OrderDto.builder()
                .orderId(savedOrder.getOrderId())
                .regTime(savedOrder.getRegTime())
                .modTime(savedOrder.getModTime())
                .deliveryDate(savedOrder.getDeliveryDate())
                .pickupDate(savedOrder.getPickupDate())
                .orderStatus(savedOrder.getOrderStatus())
                .totalPrice(savedOrder.getTotalPrice())
                .washingMethod(savedOrder.getWashingMethod())
                .build();
    }
}

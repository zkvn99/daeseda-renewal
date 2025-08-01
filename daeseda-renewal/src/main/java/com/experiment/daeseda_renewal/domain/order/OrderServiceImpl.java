package com.experiment.daeseda_renewal.domain.order;

import com.experiment.daeseda_renewal.constant.ErrorCode;
import com.experiment.daeseda_renewal.global.exception.BusinessException;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class OrderServiceImpl implements OrderService {

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

  @Override
  public OrderDto getOrderByOrderId(Long orderId) {
    Order order = orderRepository.findById(orderId)
                                 .orElseThrow(
                                     () -> new BusinessException(ErrorCode.ORDER_NOT_FOUND));
    return new OrderDto(order.getOrderId(), order.getRegTime(), order.getModTime(),
                        order.getDeliveryDate(), order.getOrderStatus(), order.getPickupDate(),
                        order.getTotalPrice(), order.getWashingMethod());
  }

  @Override
  public void deleteOrder(Long orderId) {
    Order order = orderRepository.findById(orderId)
                                 .orElseThrow(
                                     () -> new BusinessException(ErrorCode.ORDER_NOT_FOUND));
    orderRepository.delete(order);
  }

  @Override
  @Transactional
  public OrderDto updateOrderByOrderId(Long orderId, OrderDto orderDto) {
    Order order = orderRepository.findById(orderId)
                                 .orElseThrow(
                                     () -> new BusinessException(ErrorCode.ORDER_NOT_FOUND));

    order.updateFromDto(orderDto);

    return OrderDto.builder()
                   .orderId(order.getOrderId())
                   .regTime(order.getRegTime())
                   .modTime(order.getModTime())
                   .deliveryDate(order.getDeliveryDate())
                   .pickupDate(order.getPickupDate())
                   .orderStatus(order.getOrderStatus())
                   .totalPrice(order.getTotalPrice())
                   .washingMethod(order.getWashingMethod())
                   .build();
  }
}

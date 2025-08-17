package com.experiment.daeseda_renewal.domain.order;

import com.experiment.daeseda_renewal.constant.ErrorCode;
import com.experiment.daeseda_renewal.constant.OrderStatus;
import com.experiment.daeseda_renewal.constant.WashingMethod;
import com.experiment.daeseda_renewal.domain.address.Address;
import com.experiment.daeseda_renewal.domain.address.AddressRepository;
import com.experiment.daeseda_renewal.domain.order.dto.CreateOrderRequest;
import com.experiment.daeseda_renewal.domain.order.dto.OrderResponse;
import com.experiment.daeseda_renewal.domain.user.User;
import com.experiment.daeseda_renewal.domain.user.UserRepository;
import com.experiment.daeseda_renewal.global.exception.BusinessException;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    @Override
    @Transactional
    public void createOrder(CreateOrderRequest request) {
        User user = userRepository.findById(request.getUserId())
                                  .get();
        Address address = addressRepository.findById(request.getAddressId())
                                           .get();
        LocalDate deliveryDate = LocalDate.parse(request.getDeliveryDate(),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Order order = Order.builder()
                           .user(user)
                           .address(address)
                           .regTime(LocalDateTime.now())
                           .deliveryDate(deliveryDate)
                           .orderStatus(OrderStatus.valueOf(request.getOrderStatus()))
                           .totalPrice(request.getTotalPrice())
                           .washingMethod(WashingMethod.valueOf(request.getWashingMethod()))
                           .build();
        orderRepository.save(order);
    }

    @Override
    public List<OrderResponse> getMyOrderList(Long userId) {
        List<Order> orderList = orderRepository.findByUserUserId(userId);

        return orderList.stream()
                        .map(order -> OrderResponse.builder()
                                                   .orderId(order.getOrderId())
                                                   .userName(order.getUser()
                                                                  .getUserName())
                                                   .address(order.getAddress()
                                                                 .getAddressDetail())
                                                   .pickupDate(order.getPickupDate())
                                                   .deliveryDate(order.getDeliveryDate())
                                                   .orderStatus(order.getOrderStatus())
                                                   .totalPrice(order.getTotalPrice())
                                                   .washingMethod(order.getWashingMethod())
                                                   .build())
                        .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                                     .orElseThrow(
                                         () -> new BusinessException(ErrorCode.ORDER_NOT_FOUND));
        order.cancelOrder();
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

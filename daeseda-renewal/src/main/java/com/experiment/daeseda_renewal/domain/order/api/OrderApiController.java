package com.experiment.daeseda_renewal.domain.order.api;

import com.experiment.daeseda_renewal.domain.order.OrderService;
import com.experiment.daeseda_renewal.domain.order.dto.CancelOrderRequest;
import com.experiment.daeseda_renewal.domain.order.dto.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderApiController {
    private final OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<String> addOrder(@RequestBody CreateOrderRequest request) {
        orderService.createOrder(request);
        return ResponseEntity.ok("주문 성공");
    }

    @PatchMapping("/cancel")
    public ResponseEntity<String> cancelOrder(@RequestBody CancelOrderRequest request) {
        orderService.cancelOrder(request.getOrderId());
        return ResponseEntity.ok()
                             .build();
    }

    @PatchMapping("/cash")
    public ResponseEntity<String> cashOrder(@RequestBody CancelOrderRequest request) {
        orderService.cashOrder(request.getOrderId());
        return ResponseEntity.ok()
                             .build();
    }
}

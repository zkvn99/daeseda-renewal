package com.experiment.daeseda_renewal.controller;

import com.experiment.daeseda_renewal.service.order.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/place")
    public String placeOrder(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        orderService.placeOrder(userId);
        return "redirect:/order/confirmation";
    }
}

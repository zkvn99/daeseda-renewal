package com.experiment.daeseda_renewal.controller;

import com.experiment.daeseda_renewal.dto.OrderDto;
import com.experiment.daeseda_renewal.service.order.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/place")
    public String placeOrder(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
        try {
            Long userId = (Long) session.getAttribute("userId");
            OrderDto orderDto = orderService.placeOrder(userId);
            model.addAttribute("order", orderDto);
            return "/order/confirmation";
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "/cart/list";  // 장바구니가 비어있으면 다시 장바구니 페이지로 이동
        }
    }
}

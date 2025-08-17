package com.experiment.daeseda_renewal.domain.order;

import com.experiment.daeseda_renewal.constant.OrderStatus;
import com.experiment.daeseda_renewal.constant.WashingMethod;
import com.experiment.daeseda_renewal.domain.address.AddressService;
import com.experiment.daeseda_renewal.domain.address.dto.AddressResponse;
import com.experiment.daeseda_renewal.domain.order.dto.OrderResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/orders")
public class OrderController {

    private final AddressService addressService;
    private final OrderService orderService;

    @GetMapping("/form")
    public String orderForm(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");

        List<AddressResponse> addresses = addressService.getMyAddressList(userId);

        model.addAttribute("addresses", addresses);
        model.addAttribute("washingMethods", WashingMethod.values());
        model.addAttribute("orderStatus", OrderStatus.values());
        return "/order/form";
    }
    @GetMapping("/list")
    public String list(Model model, HttpSession session) {

        List<OrderResponse> orders = orderService.getMyOrderList((Long)session.getAttribute("userId"));
        model.addAttribute("orders", orders);
        return "/order/list";
    }
}
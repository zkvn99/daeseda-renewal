package com.experiment.daeseda_renewal.controller;

import com.experiment.daeseda_renewal.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class OrderController {

    private final OrderService orderService;
}

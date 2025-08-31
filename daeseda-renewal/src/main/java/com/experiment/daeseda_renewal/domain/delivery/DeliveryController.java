package com.experiment.daeseda_renewal.domain.delivery;

import com.experiment.daeseda_renewal.constant.ErrorCode;
import com.experiment.daeseda_renewal.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/deliveries")
@RequiredArgsConstructor
public class DeliveryController {
    private final DeliveryRepository deliveryRepository;

    @GetMapping("/{orderId}")
    public String deliveryDetail(@PathVariable Long orderId, Model model) {
        Delivery delivery = deliveryRepository.findByOrderOrderId(orderId)
                                              .orElseThrow(
                                                  () -> new BusinessException(
                                                      ErrorCode.DELIVERY_NOT_FOUND));
        ;

        model.addAttribute("delivery", delivery);
        return "delivery/info";
    }
}

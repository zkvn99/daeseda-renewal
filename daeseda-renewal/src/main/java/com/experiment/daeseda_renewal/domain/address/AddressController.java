package com.experiment.daeseda_renewal.domain.address;

import com.experiment.daeseda_renewal.domain.address.dto.AddressResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

  private final AddressService addressService;

  @GetMapping("/list")
  public String list(Model model) {
    List<AddressResponse> addresses = addressService.getMyAddressList(1L);
    model.addAttribute("addresses", addresses);
    return "/address/list";
  }

  @GetMapping("/form")
  public String addForm() {
    return "/address/form";
  }

  @DeleteMapping("/delete")
  public String delete(@RequestParam("orderId") Long orderId, @RequestParam("userId") Long userId) {
    System.out.println("orderId --> " + orderId);
    System.out.println("userId --> " + userId);

    addressService.delete(orderId, userId);
    return "redirect:/address/list";
  }
}

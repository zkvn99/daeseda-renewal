package com.experiment.daeseda_renewal.domain.address;

import com.experiment.daeseda_renewal.domain.address.dto.AddressResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/addresses")
public class AddressController {

  private final AddressService addressService;

  @GetMapping("/list")
  public String list(Model model, HttpSession session) {
    List<AddressResponse> addresses = addressService.getMyAddressList(
        (Long) session.getAttribute("userId"));
    model.addAttribute("addresses", addresses);
    return "/address/list";
  }

  @GetMapping("/form")
  public String addForm() {
    return "/address/form";
  }
}

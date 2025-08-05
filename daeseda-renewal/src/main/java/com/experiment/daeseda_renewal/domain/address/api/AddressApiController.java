package com.experiment.daeseda_renewal.domain.address.api;

import com.experiment.daeseda_renewal.domain.address.AddressService;
import com.experiment.daeseda_renewal.domain.address.dto.CreateAddressRequest;
import com.experiment.daeseda_renewal.domain.address.dto.DeleteAddressRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/address")
public class AddressApiController {

  private final AddressService addressService;

  @PostMapping("/add")
  public ResponseEntity<String> add(@RequestBody CreateAddressRequest request) {
    addressService.createAddress(request);
    return ResponseEntity.status(HttpStatus.CREATED)
                         .body("주소가 등록되었습니다.");
  }

  @DeleteMapping("/delete")
  public String delete(@RequestBody DeleteAddressRequest request) {
    addressService.delete(request);
    return "redirect:/address/list";
  }
}

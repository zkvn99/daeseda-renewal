package com.experiment.daeseda_renewal.domain.address.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DeleteAddressRequest {

  private final Long userId;
  private final Long addressId;

  @Builder
  public DeleteAddressRequest(Long userId, Long addressId) {
    this.userId = userId;
    this.addressId = addressId;
  }
}

package com.experiment.daeseda_renewal.domain.address.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AddressResponse {

  private final Long addressId;
  private final String addressName;
  private final String addressDetail;
  private final String addressZipcode;
  private final String addressRoad;
  private final boolean defaultAddress;
  private final Long userId;

  @Builder
  public AddressResponse(Long addressId, String addressName, String addressDetail,
      String addressZipcode, String addressRoad, boolean defaultAddress, Long userId) {
    this.addressId = addressId;
    this.addressName = addressName;
    this.addressDetail = addressDetail;
    this.addressZipcode = addressZipcode;
    this.addressRoad = addressRoad;
    this.defaultAddress = defaultAddress;
    this.userId = userId;
  }
}

package com.experiment.daeseda_renewal.domain.address.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressResponse {

  private final Long addressId;
  private final String addressName;
  private final String addressDetail;
  private final String addressZipcode;
  private final String addressRoad;
  private final boolean defaultAddress;
  private final Long userId;
}

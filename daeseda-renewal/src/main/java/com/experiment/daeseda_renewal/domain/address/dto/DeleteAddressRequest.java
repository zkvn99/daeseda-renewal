package com.experiment.daeseda_renewal.domain.address.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeleteAddressRequest {

  private Long userId;
  private Long addressId;
}

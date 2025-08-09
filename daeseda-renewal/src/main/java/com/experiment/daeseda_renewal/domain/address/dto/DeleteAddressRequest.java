package com.experiment.daeseda_renewal.domain.address.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeleteAddressRequest {

  private final Long userId;
  private final Long addressId;
}

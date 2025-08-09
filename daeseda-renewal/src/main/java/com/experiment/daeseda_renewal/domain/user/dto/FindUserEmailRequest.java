package com.experiment.daeseda_renewal.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FindUserEmailRequest {

  private final String userName;
  private final String userPhone;
}

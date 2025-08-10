package com.experiment.daeseda_renewal.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResetUserPasswordRequest {

  private String userEmail;
  private String userPassword;
}

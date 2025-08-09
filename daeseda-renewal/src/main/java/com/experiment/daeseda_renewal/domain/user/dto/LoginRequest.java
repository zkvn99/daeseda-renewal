package com.experiment.daeseda_renewal.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequest {

  private final String userEmail;
  private final String userPassword;
}

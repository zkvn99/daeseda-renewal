package com.experiment.daeseda_renewal.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VerifyUserPasswordResetRequest {

  private String userName;
  private String userEmail;
  private String userPhone;
}

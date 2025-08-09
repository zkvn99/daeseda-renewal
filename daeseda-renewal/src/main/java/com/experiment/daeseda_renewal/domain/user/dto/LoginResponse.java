package com.experiment.daeseda_renewal.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {

  private final Long userId;
  private final String userEmail;
  private final String userNickname;
}

package com.experiment.daeseda_renewal.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUserRequest {

  private final String userEmail;
  private final String userName;
  private final String userPassword;
  private final String userPhone;
  private final String userNickname;
}

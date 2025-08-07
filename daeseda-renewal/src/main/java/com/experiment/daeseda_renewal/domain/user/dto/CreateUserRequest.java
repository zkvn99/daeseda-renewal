package com.experiment.daeseda_renewal.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUserRequest {

  private String userEmail;
  private String userName;
  private String userPassword;
  private String userPhone;
  private String userNickname;
}

package com.experiment.daeseda_renewal.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeleteUserRequest {

  private Long userId;
  private String userEmail;
  private String userPassword;
}

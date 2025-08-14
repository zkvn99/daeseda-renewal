package com.experiment.daeseda_renewal.domain.user.dto;

import java.io.Serializable;

public record LoginUserSnapshot(Long userId, String userNickname, String userEmail) implements
    Serializable {

  private static final long serialVersionUID = 1L;
}

package com.experiment.daeseda_renewal.domain.board.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostUpdateRequest {

  private final String postTitle;
  private final String postContent;
  private final String userNickname;
}

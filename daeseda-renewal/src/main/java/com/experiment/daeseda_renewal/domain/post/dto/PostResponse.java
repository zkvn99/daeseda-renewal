package com.experiment.daeseda_renewal.domain.post.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostResponse {

  private final Long postId;
  private final String postTitle;
  private final String postContent;
  private final String userNickname;
  private final Long views;
}

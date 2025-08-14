package com.experiment.daeseda_renewal.domain.post;

import com.experiment.daeseda_renewal.domain.post.dto.PostCreateRequest;
import com.experiment.daeseda_renewal.domain.post.dto.PostResponse;
import com.experiment.daeseda_renewal.domain.post.dto.PostUpdateRequest;
import com.experiment.daeseda_renewal.domain.user.dto.LoginUserSnapshot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

  Page<PostResponse> searchPosts(String q, Pageable pageable);

  PostResponse getPost(Long postId);

  PostResponse getAndIncreaseViews(Long postId);

  PostResponse createPost(PostCreateRequest request, LoginUserSnapshot user);

  PostResponse updatePost(Long postId, PostUpdateRequest request);

  void deletePost(Long postId);
}

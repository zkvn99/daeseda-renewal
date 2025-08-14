package com.experiment.daeseda_renewal.domain.post;

import com.experiment.daeseda_renewal.domain.post.dto.PostCreateRequest;
import com.experiment.daeseda_renewal.domain.post.dto.PostResponse;
import com.experiment.daeseda_renewal.domain.post.dto.PostUpdateRequest;
import com.experiment.daeseda_renewal.domain.user.dto.LoginUserSnapshot;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;

  @Override
  public Page<PostResponse> searchPosts(String q, Pageable pageable) {
    return postRepository.search(q, pageable)
                         .map(PostServiceImpl::toDto);
  }

  @Override
  public PostResponse getPost(Long id) {
    Post p = postRepository.findById(id)
                           .orElseThrow(() -> new IllegalArgumentException("POST_NOT_FOUND"));
    return toDto(p);
  }

  @Override
  @Transactional
  public PostResponse getAndIncreaseViews(Long id) {
    Post p = postRepository.findById(id)
                           .orElseThrow(() -> new IllegalArgumentException("POST_NOT_FOUND"));
    p.increaseViews();
    return toDto(p);
  }

  @Override
  @Transactional
  public PostResponse createPost(PostCreateRequest request, LoginUserSnapshot user) {
    Post p = Post.builder()
                 .postTitle(request.getPostTitle())
                 .postContent(request.getPostContent())
                 .userNickname(user.userNickname())
                 .userId(user.userId())
                 .views(0L)
                 .build();
    return toDto(postRepository.save(p));
  }

  @Override
  @Transactional
  public PostResponse updatePost(Long id, PostUpdateRequest request) {
    Post p = postRepository.findById(id)
                           .orElseThrow(() -> new IllegalArgumentException("POST_NOT_FOUND"));
    p.update(request.getPostTitle(), request.getPostContent());
    return toDto(p);
  }

  @Override
  @Transactional
  public void deletePost(Long id) {
    postRepository.deleteById(id);
  }

  private static PostResponse toDto(Post p) {
    return PostResponse.builder()
                       .postId(p.getPostId())
                       .postTitle(p.getPostTitle())
                       .postContent(p.getPostContent())
                       .userNickname(p.getUserNickname())
                       .views(p.getViews())
                       .build();
  }

}

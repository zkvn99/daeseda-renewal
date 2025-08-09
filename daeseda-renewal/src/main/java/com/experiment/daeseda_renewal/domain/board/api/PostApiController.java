package com.experiment.daeseda_renewal.domain.board.api;

import com.experiment.daeseda_renewal.domain.board.PostService;
import com.experiment.daeseda_renewal.domain.board.dto.PagedResponse;
import com.experiment.daeseda_renewal.domain.board.dto.PostCreateRequest;
import com.experiment.daeseda_renewal.domain.board.dto.PostResponse;
import com.experiment.daeseda_renewal.domain.board.dto.PostUpdateRequest;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts/")
public class PostApiController {

  private final PostService postService;

  @GetMapping
  public PagedResponse<PostResponse> list(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(required = false) String q,
      @RequestParam(defaultValue = "latest") String sort
  ) {
    Sort s = switch (sort) {
      case "oldest" -> Sort.by("id")
                           .ascending();
      case "mostViewed" -> Sort.by(Sort.Direction.DESC, "views", "id");
      default -> Sort.by("id")
                     .descending();
    };
    PageRequest pr = PageRequest.of(page, size, s);
    Page<PostResponse> result = postService.searchPosts(q, pr);
    return PagedResponse.of(result);
  }

  @GetMapping("/{id}")
  public PostResponse get(@PathVariable Long id,
      @RequestParam(defaultValue = "false") boolean increaseViews) {
    return increaseViews ? postService.getAndIncreaseViews(id) : postService.getPost(id);
  }

  @PostMapping
  public PostResponse create(@Valid @RequestBody PostCreateRequest req) {
    return postService.createPost(req);
  }

  @PutMapping("/{id}")
  public PostResponse update(@PathVariable Long id,
      @Valid @RequestBody PostUpdateRequest req) {
    return postService.updatePost(id, req);
  }

  @DeleteMapping("/{id}")
  public Map<String, Object> delete(@PathVariable Long id) {
    postService.deletePost(id);
    return Map.of("success", true);
  }
}

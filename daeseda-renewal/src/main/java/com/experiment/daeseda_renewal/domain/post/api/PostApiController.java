package com.experiment.daeseda_renewal.domain.post.api;

import com.experiment.daeseda_renewal.domain.post.PostService;
import com.experiment.daeseda_renewal.domain.post.dto.PostCreateRequest;
import com.experiment.daeseda_renewal.domain.post.dto.PostResponse;
import com.experiment.daeseda_renewal.domain.post.dto.PostUpdateRequest;
import com.experiment.daeseda_renewal.domain.user.dto.LoginUserSnapshot;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/api/posts")
public class PostApiController {

  private final Logger log = LoggerFactory.getLogger(PostApiController.class);
  private final PostService postService;

  @GetMapping
  public Page<PostResponse> listPosts(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(required = false) String q
//      @RequestParam(defaultValue = "latest") String sort
  ) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("id")
                                                       .descending());

    return postService.searchPosts(q, pageable);
  }

  @PostMapping
  public PostResponse add(@Valid @RequestBody PostCreateRequest req, HttpSession session) {
    LoginUserSnapshot user = (LoginUserSnapshot) session.getAttribute("LOGIN_USER");
    return postService.createPost(req, user);
  }

  @PutMapping("/{id:\\\\d+}")
  public PostResponse update(@PathVariable Long id,
      @Valid @RequestBody PostUpdateRequest req) {
    return postService.updatePost(id, req);
  }

  @DeleteMapping("/{id:\\\\d+}")
  public Map<String, Object> delete(@PathVariable Long id) {
    postService.deletePost(id);
    return Map.of("success", true);
  }
}

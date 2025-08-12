package com.experiment.daeseda_renewal.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

  @GetMapping("/list")
  public String postList() {
    return "/post/list";
  }

  @GetMapping("/form")
  public String postForm() {
    return "/post/form";
  }

  @GetMapping("/{id:\\\\d+}")
  public String detailPage(@PathVariable Long id) {
    return "/post/detail";
  }

  @GetMapping("/{id:\\\\d+}/edit")
  public String editPage(@PathVariable Long id) {
    return "/post/form";
  }
}

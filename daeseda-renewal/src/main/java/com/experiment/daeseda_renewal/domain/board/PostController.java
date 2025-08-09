package com.experiment.daeseda_renewal.domain.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

  @GetMapping
  public String listPage() {
    return "/post/list";
  }

  @GetMapping("/new")
  public String createPage() {
    return "/post/form";
  }

  @GetMapping("/{id}")
  public String detailPage(@PathVariable Long id) {
    return "/post/detail";
  }

  @GetMapping("/{id}/edit")
  public String editPage(@PathVariable Long id) {
    return "/form";
  }
}

package com.experiment.daeseda_renewal.domain.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/users")
@Controller
public class UserController {

  private final UserService userService;

  @GetMapping("/signup")
  public String signupForm() {
    return "/user/signup";
  }

  @GetMapping("/login")
  public String loginForm() {
    return "/user/login";
  }

  @GetMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/";
  }

  @GetMapping("/my-page")
  public String myPage(HttpSession session) {
    if (session.getAttribute("userId") == null) {
      return "redirect:/login";  // 로그인 상태가 아니면 로그인 페이지로 리디렉션
    }
    return "/user/my-page";  // 마이페이지를 반환
  }

  @GetMapping("/find-id")
  public String findIdForm() {
    return "/user/find-id";
  }

  @GetMapping("/find-pw")
  public String findPasswordForm() {
    return "/user/find-pw";
  }

}

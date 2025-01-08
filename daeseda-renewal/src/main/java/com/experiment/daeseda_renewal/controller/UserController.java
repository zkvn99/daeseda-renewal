package com.experiment.daeseda_renewal.controller;

import com.experiment.daeseda_renewal.dto.UserDTO;
import com.experiment.daeseda_renewal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signupForm() {
        return "/user/signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute UserDTO userDTO) {
        userService.signUp(userDTO);
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "/user/login";
    }

    @PostMapping("/login")
    public String login() {
        return "index";
    }

    @PostMapping("/logout")
    public String logout() {
        return "index";
    }
}

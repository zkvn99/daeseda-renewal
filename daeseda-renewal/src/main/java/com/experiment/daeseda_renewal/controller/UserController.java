package com.experiment.daeseda_renewal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/logout")
    public String logout() {
        return "index";
    }
}

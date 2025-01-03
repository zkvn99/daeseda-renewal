package com.experiment.daeseda_renewal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}

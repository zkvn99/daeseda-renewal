package com.experiment.daeseda_renewal.domain.clothes;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clothes")
@RequiredArgsConstructor
public class ClothesController {

    private final ClothesService clothesService;

    @GetMapping("/list")
    public String listClothes(Model model, HttpSession session) {
        List<Clothes> clothes = clothesService.getAllClothes();
        model.addAttribute("clothes", clothes);
        return "clothes/list";
    }









}

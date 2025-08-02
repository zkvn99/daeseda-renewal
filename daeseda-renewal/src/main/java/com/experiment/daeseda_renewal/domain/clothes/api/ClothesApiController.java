package com.experiment.daeseda_renewal.domain.clothes.api;

import com.experiment.daeseda_renewal.domain.clothes.ClothesService;
import com.experiment.daeseda_renewal.domain.clothes.CreateClothesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clothes")
@RequiredArgsConstructor
public class ClothesApiController {
    private final ClothesService clothesService;

    @PostMapping("/register")
    public ResponseEntity<String> registerClothes(@RequestBody CreateClothesDTO clothesDTO) {
        clothesService.createClothes(clothesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

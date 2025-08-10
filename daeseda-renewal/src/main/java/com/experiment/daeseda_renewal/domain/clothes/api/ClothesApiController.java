package com.experiment.daeseda_renewal.domain.clothes.api;

import com.experiment.daeseda_renewal.domain.clothes.ClothesService;
import com.experiment.daeseda_renewal.domain.clothes.dto.CreateClothesRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clothes")
@RequiredArgsConstructor
public class ClothesApiController {

  private final ClothesService clothesService;

  // 의류 생성
  @PostMapping("/register")
  public ResponseEntity<String> registerClothes(@RequestBody CreateClothesRequest clothesDTO) {
    clothesService.createClothes(clothesDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  // 의류 삭제
  @DeleteMapping("/{clothesId}")
  public ResponseEntity<String> deleteClothes(@PathVariable Long clothesId) {
    if (clothesService.deleteClothes(clothesId) > 0) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body("삭제가 성공적으로 실행되었습니다");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("삭제에 실패했습니다.");
    }
  }
}

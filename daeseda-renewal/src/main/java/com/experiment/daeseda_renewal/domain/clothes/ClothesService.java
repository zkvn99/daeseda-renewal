package com.experiment.daeseda_renewal.domain.clothes;

import com.experiment.daeseda_renewal.domain.category.Category;
import com.experiment.daeseda_renewal.domain.clothes.dto.CreateClothesRequest;
import java.util.List;

public interface ClothesService {

  // 리스트 조회
  List<Clothes> getAllClothes();

  // 의류 생성
  Clothes createClothes(CreateClothesRequest clothesDTO);

  CreateClothesRequest getClothesById(Long clothesById);

  // 의류 삭제
  int deleteClothes(Long clothesId);

  default Clothes convertToEntity(CreateClothesRequest createClothesRequest) {
    Category categoryEntity = Category.builder()
        .categoryById(createClothesRequest.getCategoryId())
        .build();

    Clothes clothesEntity = Clothes.builder()
        .id(createClothesRequest.getClothesId())
        .name(createClothesRequest.getClothesName())
        .laundryCost(createClothesRequest.getClothesPrice())
        .build();

    return clothesEntity;
  }
}

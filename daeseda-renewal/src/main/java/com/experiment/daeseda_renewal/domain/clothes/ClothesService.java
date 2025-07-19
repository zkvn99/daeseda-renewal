package com.experiment.daeseda_renewal.domain.clothes;

import com.experiment.daeseda_renewal.domain.category.Category;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ClothesService {

    List<Clothes> getAllClothes();

    Clothes createClothes(CreateClothesDTO clothesDTO);

    CreateClothesDTO getClothesById(Long clothesById);

    default Clothes convertToEntity(CreateClothesDTO createClothesDTO) {
        Category categoryEntity = Category.builder()
                .categoryById(createClothesDTO.getCategoryId())
                .build();

        Clothes clothesEntity = Clothes.builder()
                .id(createClothesDTO.getClothesId())
                .name(createClothesDTO.getClothesName())
                .laundryCost(createClothesDTO.getClothesPrice())
                .build();

        return clothesEntity;
    }
}

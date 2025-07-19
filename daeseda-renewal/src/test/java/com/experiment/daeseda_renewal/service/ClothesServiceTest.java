package com.experiment.daeseda_renewal.service;

import com.experiment.daeseda_renewal.domain.clothes.Clothes;
import com.experiment.daeseda_renewal.domain.clothes.ClothesService;
import com.experiment.daeseda_renewal.domain.clothes.CreateClothesDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

@SpringBootTest
public class ClothesServiceTest {

    @Autowired
    private ClothesService clothesService;

    @Test
    public void testCreateProduct() {

        for(int i=0; i<10; i++){
            // Given
            String clothesName = "TestProduct" + i;
            BigDecimal clothesPrice = new BigDecimal("12000");
            CreateClothesDTO clothesDTO = CreateClothesDTO.builder()
                    .clothesPrice(clothesPrice)
                    .clothesName(clothesName)
                    .build();

            // When
            Clothes savedClothes = clothesService.createClothes(clothesDTO);

            // Then
            assertThat(savedClothes).isNotNull();
            assertThat(savedClothes.getId()).isNotNull();
            assertThat(savedClothes.getName()).isEqualTo(clothesName);
            assertThat(savedClothes.getLaundryCost()).isEqualTo(clothesPrice);
        }
    }

}

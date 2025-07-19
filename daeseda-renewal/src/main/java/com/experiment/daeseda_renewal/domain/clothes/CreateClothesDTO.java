package com.experiment.daeseda_renewal.domain.clothes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateClothesDTO {
    private Long clothesId;

    private String clothesName;

    private BigDecimal clothesPrice;

    private Long categoryId;
}

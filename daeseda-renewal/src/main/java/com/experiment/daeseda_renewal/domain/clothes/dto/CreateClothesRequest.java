package com.experiment.daeseda_renewal.domain.clothes.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateClothesRequest {

  private Long clothesId;

  private String clothesName;

  private BigDecimal clothesPrice;

  private Long categoryId;
}

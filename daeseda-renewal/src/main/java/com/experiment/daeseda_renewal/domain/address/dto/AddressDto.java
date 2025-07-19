package com.experiment.daeseda_renewal.domain.address.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressDto {
    private String addressName;
    private String addressDetail;
    private int addressZipcode;
    private String addressRoad;
}

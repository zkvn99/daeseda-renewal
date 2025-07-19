package com.experiment.daeseda_renewal.domain.address.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateAddressRequest {
    private String addressName;
    private String addressDetail;
    private String addressZipcode;
    private String addressRoad;
    private boolean defaultAddress;
}

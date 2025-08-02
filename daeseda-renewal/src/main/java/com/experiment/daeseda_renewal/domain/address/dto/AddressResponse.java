package com.experiment.daeseda_renewal.domain.address.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressResponse {
    private Long addressId;
    @NotBlank(message = "주소 상세 정보는 필수입니다.")
    private String addressName;
    private String addressDetail;
    @NotBlank(message = "우편번호는 필수입니다.")
    @Pattern(regexp = "\\d{5}", message = "우편번호는 5자리 숫자여야 합니다.")
    private String addressZipcode;
    @NotBlank(message = "도로명 주소는 필수입니다.")
    private String addressRoad;
    private boolean defaultAddress;
    @NotBlank(message = "유저 아이디 정보는 필수입니다.")
    private Long userId;
}

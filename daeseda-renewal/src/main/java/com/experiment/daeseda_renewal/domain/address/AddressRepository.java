package com.experiment.daeseda_renewal.domain.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    boolean existsByAddressZipcodeAndAddressDetail(String addressZipcode, String addressDetail);
}

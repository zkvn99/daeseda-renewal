package com.experiment.daeseda_renewal.domain.address;

import com.experiment.daeseda_renewal.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    boolean existsByAddressZipcodeAndAddressDetail(String addressZipcode, String addressDetail);
    List<Address> findByUser(User user);
}

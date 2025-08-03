package com.experiment.daeseda_renewal.domain.address;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

  boolean existsByAddressZipcodeAndAddressDetail(String addressZipcode, String addressDetail);

  List<Address> findByUserId(Long userId);
}

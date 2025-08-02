package com.experiment.daeseda_renewal.domain.address;

import com.experiment.daeseda_renewal.domain.user.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

  boolean existsByAddressZipcodeAndAddressDetail(String addressZipcode, String addressDetail);

  List<Address> findByUser(User user);
}

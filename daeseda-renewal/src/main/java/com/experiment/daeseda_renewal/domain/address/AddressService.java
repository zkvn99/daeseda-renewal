package com.experiment.daeseda_renewal.domain.address;

import com.experiment.daeseda_renewal.domain.address.dto.AddressResponse;
import com.experiment.daeseda_renewal.domain.address.dto.CreateAddressRequest;
import java.util.List;

public interface AddressService {

  void createAddress(CreateAddressRequest addressDto);

  List<AddressResponse> getMyAddressList(Long userId);

  void delete(Long addressId, Long userId);
}

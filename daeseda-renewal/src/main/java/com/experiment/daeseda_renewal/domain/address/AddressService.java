package com.experiment.daeseda_renewal.domain.address;

import com.experiment.daeseda_renewal.domain.address.dto.AddressResponse;
import com.experiment.daeseda_renewal.domain.address.dto.CreateAddressRequest;
import com.experiment.daeseda_renewal.domain.address.dto.DeleteAddressRequest;
import java.util.List;

public interface AddressService {

  void createAddress(CreateAddressRequest request);

  List<AddressResponse> getMyAddressList(Long userId);

  void delete(DeleteAddressRequest request);
}

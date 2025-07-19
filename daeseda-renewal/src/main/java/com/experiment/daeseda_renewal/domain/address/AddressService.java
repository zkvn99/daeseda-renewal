package com.experiment.daeseda_renewal.domain.address;

import com.experiment.daeseda_renewal.domain.address.dto.CreateAddressRequest;

import java.util.List;

public interface AddressService {

    boolean createAddress(CreateAddressRequest addressDto);

    List<CreateAddressRequest> getMyAddressList();

    boolean delete(CreateAddressRequest addressDto);
}

package com.experiment.daeseda_renewal.domain.address;

import com.experiment.daeseda_renewal.domain.address.dto.AddressDto;

import java.util.List;

public interface AddressService {

    boolean createAddress(AddressDto addressDto);

    List<AddressDto> getMyAddressList();

    boolean delete(AddressDto addressDto);
}

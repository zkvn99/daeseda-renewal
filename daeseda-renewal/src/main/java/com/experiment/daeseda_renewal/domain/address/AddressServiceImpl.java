package com.experiment.daeseda_renewal.domain.address;

import com.experiment.daeseda_renewal.domain.address.dto.CreateAddressRequest;

import java.util.List;

public class AddressServiceImpl implements AddressService{
    @Override
    public boolean createAddress(CreateAddressRequest addressDto) {
        return false;
    }

    @Override
    public List<CreateAddressRequest> getMyAddressList() {
        return null;
    }

    @Override
    public boolean delete(CreateAddressRequest addressDto) {
        return false;
    }
}

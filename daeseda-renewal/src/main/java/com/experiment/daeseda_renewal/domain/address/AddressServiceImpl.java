package com.experiment.daeseda_renewal.domain.address;

import com.experiment.daeseda_renewal.domain.address.dto.CreateAddressRequest;
import com.experiment.daeseda_renewal.global.exception.AddressException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService{

    private final AddressRepository addressRepository;

    @Override
    public boolean createAddress(CreateAddressRequest addressDto) {

        boolean exists = addressRepository.existsByAddressZipcodeAndAddressDetail(
                addressDto.getAddressZipcode(),
                addressDto.getAddressDetail()
        );

        if (exists) {
            throw new AddressException("이미 등록된 주소입니다.");
        }

        Address address = Address.builder()
                .addressDetail(addressDto.getAddressDetail())
                .addressZipcode(addressDto.getAddressZipcode())
                .addressName(addressDto.getAddressName())
                .addressRoad(addressDto.getAddressRoad())
                .build();

        try {
            addressRepository.save(address);
            return true;
        } catch (Exception e) {
            throw new AddressException("주소 저장 중 오류가 발생했습니다.", e);
        }

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

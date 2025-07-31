package com.experiment.daeseda_renewal.domain.address;

import com.experiment.daeseda_renewal.domain.address.dto.AddressResponse;
import com.experiment.daeseda_renewal.domain.address.dto.CreateAddressRequest;
import com.experiment.daeseda_renewal.domain.user.User;
import com.experiment.daeseda_renewal.domain.user.UserRepository;
import com.experiment.daeseda_renewal.global.exception.AddressException;
import com.experiment.daeseda_renewal.global.exception.NotFoundException;
import com.experiment.daeseda_renewal.global.exception.UnauthorizedAccessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService{

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Override
    public void createAddress(CreateAddressRequest addressDto) {

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
        } catch (Exception e) {
            throw new AddressException("주소 저장 중 오류가 발생했습니다.", e);
        }

    }

    @Override
    public List<AddressResponse> getMyAddressList(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("해당 사용자가 존재하지 않습니다."));

        List<Address> addressList = addressRepository.findByUser(user);

        return addressList.stream()
                .map(address -> AddressResponse.builder()
                        .addressId(address.getAddressId())
                        .addressDetail(address.getAddressDetail())
                        .addressZipcode(address.getAddressZipcode())
                        .addressName(address.getAddressName())
                        .addressRoad(address.getAddressRoad())
                        .userId(user.getId())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long addressId, Long userId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new NotFoundException("주소가 존재하지 않습니다."));

        if (!address.getUser().getId().equals(userId)) {
            throw new UnauthorizedAccessException("본인의 주소만 삭제할 수 있습니다.");
        }

        try {
            addressRepository.delete(address);
        } catch (Exception e) {
            throw new AddressException("주소 삭제 중 오류가 발생했습니다.", e);
        }
    }
}

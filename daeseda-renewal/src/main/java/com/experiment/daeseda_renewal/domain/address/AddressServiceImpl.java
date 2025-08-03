package com.experiment.daeseda_renewal.domain.address;

import com.experiment.daeseda_renewal.constant.ErrorCode;
import com.experiment.daeseda_renewal.domain.address.dto.AddressResponse;
import com.experiment.daeseda_renewal.domain.address.dto.CreateAddressRequest;
import com.experiment.daeseda_renewal.global.exception.BusinessException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

  private final AddressRepository addressRepository;

  @Override
  public void createAddress(CreateAddressRequest request) {
    boolean exists = addressRepository.existsByAddressZipcodeAndAddressDetail(
        request.getAddressZipcode(), request.getAddressDetail());

    if (exists) {
      throw new BusinessException(ErrorCode.DUPLICATE_ADDR);
    }

    Address address = Address.builder()
                             .addressDetail(request.getAddressDetail())
                             .addressZipcode(request.getAddressZipcode())
                             .addressName(request.getAddressName())
                             .addressRoad(request.getAddressRoad())
                             .userId(request.getUserId())
                             .build();

    addressRepository.save(address);
  }

  @Override
  public List<AddressResponse> getMyAddressList(Long userId) {

    List<Address> addressList = addressRepository.findByUserId(userId);

    return addressList.stream()
                      .map(address -> AddressResponse.builder()
                                                     .addressId(address.getAddressId())
                                                     .addressDetail(address.getAddressDetail())
                                                     .addressZipcode(address.getAddressZipcode())
                                                     .addressName(address.getAddressName())
                                                     .addressRoad(address.getAddressRoad())
                                                     .userId(address.getUserId())
                                                     .build())
                      .collect(Collectors.toList());
  }

  @Override
  public void delete(Long addressId, Long userId) {

    Address address = addressRepository.findById(addressId)
                                       .orElseThrow(
                                           () -> new BusinessException(ErrorCode.ADDR_NOT_FOUND));

    if (!address.getUserId()
                .equals(userId)) {
      throw new BusinessException(ErrorCode.ADDR_DELETE_FORBIDDEN);
    }

    addressRepository.delete(address);
  }
}

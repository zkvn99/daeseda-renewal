package com.experiment.daeseda_renewal.domain.address;

import com.experiment.daeseda_renewal.constant.ErrorCode;
import com.experiment.daeseda_renewal.domain.address.dto.AddressResponse;
import com.experiment.daeseda_renewal.domain.address.dto.CreateAddressRequest;
import com.experiment.daeseda_renewal.domain.user.User;
import com.experiment.daeseda_renewal.domain.user.UserRepository;
import com.experiment.daeseda_renewal.global.exception.BusinessException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

  private final AddressRepository addressRepository;
  private final UserRepository userRepository;

  @Override
  public void createAddress(CreateAddressRequest addressDto) {
    boolean exists = addressRepository.existsByAddressZipcodeAndAddressDetail(
        addressDto.getAddressZipcode(), addressDto.getAddressDetail());

    if (exists) {
      throw new BusinessException(ErrorCode.DUPLICATE_ADDR);
    }

    Address address = Address.builder()
                             .addressDetail(addressDto.getAddressDetail())
                             .addressZipcode(addressDto.getAddressZipcode())
                             .addressName(addressDto.getAddressName())
                             .addressRoad(addressDto.getAddressRoad())
                             .build();

    addressRepository.save(address);
  }

  @Override
  public List<AddressResponse> getMyAddressList(Long userId) {

    User user = userRepository.findById(userId)
                              .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

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
                                       .orElseThrow(
                                           () -> new BusinessException(ErrorCode.ADDR_NOT_FOUND));
    if (!address.getUser()
                .getId()
                .equals(userId)) {
      throw new BusinessException(ErrorCode.ADDR_DELETE_FORBIDDEN);
    }
    addressRepository.delete(address);
  }
}

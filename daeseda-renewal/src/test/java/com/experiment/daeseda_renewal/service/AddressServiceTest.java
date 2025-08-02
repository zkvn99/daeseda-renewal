package com.experiment.daeseda_renewal.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.experiment.daeseda_renewal.domain.address.Address;
import com.experiment.daeseda_renewal.domain.address.AddressRepository;
import com.experiment.daeseda_renewal.domain.address.AddressServiceImpl;
import com.experiment.daeseda_renewal.domain.address.dto.AddressResponse;
import com.experiment.daeseda_renewal.domain.address.dto.CreateAddressRequest;
import com.experiment.daeseda_renewal.domain.user.User;
import com.experiment.daeseda_renewal.domain.user.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

  // 실제 구현 객체를 생성하기 때문에 구현체 삽입
  @InjectMocks
  private AddressServiceImpl addressService;

  @Mock
  private AddressRepository addressRepository;

  @Mock
  private UserRepository userRepository;

  @Captor
  private ArgumentCaptor<Address> addressCaptor;


  @Test
  public void createAddress_multipleSuccess() {

    // Given
    when(addressRepository.existsByAddressZipcodeAndAddressDetail(any(), any()))
        .thenReturn(false); // 중복 검사를 모두 false로 처리

//        when(addressRepository.save(any(Address.class)))
//                .thenReturn(mock(Address.class)); // save도 정상적으로 진행

    List<CreateAddressRequest> testData = IntStream.range(0, 10)
                                                   .mapToObj(i -> CreateAddressRequest.builder()
                                                                                      .addressDetail(
                                                                                          "detail"
                                                                                              + i)
                                                                                      .addressRoad(
                                                                                          "road"
                                                                                              + i)
                                                                                      .addressName(
                                                                                          "name"
                                                                                              + i)
                                                                                      .addressZipcode(
                                                                                          "2001"
                                                                                              + i)
                                                                                      .defaultAddress(
                                                                                          false)
                                                                                      .build())
                                                   .toList();

    // When
    for (CreateAddressRequest address : testData) {
      addressService.createAddress(address);
    }

    // Then
    verify(addressRepository, times(10)).save(addressCaptor.capture()); // 10개의 Address 객체 캡처

    List<Address> savedAddresses = addressCaptor.getAllValues(); // 저장된 객체 리스트 가져오기

    assertEquals(10, savedAddresses.size());

    // 실제로 저장된 값 출력 및 검증
    for (int i = 0; i < 10; i++) {
      Address saved = savedAddresses.get(i);
      assertEquals("detail" + i, saved.getAddressDetail());
      assertEquals("road" + i, saved.getAddressRoad());
      assertEquals("name" + i, saved.getAddressName());
      assertEquals("2001" + i, saved.getAddressZipcode());

      System.out.printf("[%d] name=%s, zipcode=%s%n", i, saved.getAddressName(),
                        saved.getAddressZipcode());
    }
  }

  @Test
  void getMyAddressList_success() {
    // Given
    Long userId = 1L;
    User mockUser = User.builder()
                        .id(userId)
                        .build();

    when(userRepository.findById(userId))
        .thenReturn(Optional.of(mockUser));

    List<Address> addressList = IntStream.range(0, 5)
                                         .mapToObj(i -> Address.builder()
                                                               .addressId((long) i)
                                                               .addressDetail("detail" + i)
                                                               .addressRoad("road" + i)
                                                               .addressName("name" + i)
                                                               .addressZipcode("2001" + i)
                                                               .user(mockUser)
                                                               .build())
                                         .toList();

    when(addressRepository.findByUser(mockUser))
        .thenReturn(addressList);

    // When
    List<AddressResponse> result = addressService.getMyAddressList(userId);

    // Then
    assertEquals(5, result.size());

    for (int i = 0; i < 5; i++) {
      AddressResponse dto = result.get(i);
      assertEquals(i, dto.getAddressId());
      assertEquals("detail" + i, dto.getAddressDetail());
      assertEquals("road" + i, dto.getAddressRoad());
      assertEquals("name" + i, dto.getAddressName());
      assertEquals("2001" + i, dto.getAddressZipcode());
      assertEquals(userId, dto.getUserId());

      System.out.printf("[%d] id=%s, name=%s, user=%s%n", i, dto.getAddressId(),
                        dto.getAddressName(), dto.getUserId());
    }
  }

}

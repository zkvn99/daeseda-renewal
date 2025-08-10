package com.experiment.daeseda_renewal.domain.clothes;

import com.experiment.daeseda_renewal.domain.clothes.dto.CreateClothesRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClothesServiceImpl implements ClothesService {

  private final ClothesRepository clothesRepository;

  // 의류 리스트 조회
  @Override
  public List<Clothes> getAllClothes() {
    return clothesRepository.findAll();
  }

  // 의류 생성
  @Override
  public Clothes createClothes(CreateClothesRequest createClothesRequest) {
    Clothes clothes = convertToEntity(createClothesRequest);
    Clothes savedClothes = clothesRepository.save(clothes);
    return savedClothes;
  }

  @Override
  public CreateClothesRequest getClothesById(Long clothesById) {
    return null;
  }

  // 의류 삭제
  @Override
  public int deleteClothes(Long clothesId) {
    try {
      clothesRepository.deleteById(clothesId);
      return 1; // 삭제 성공 시 1 반환
    } catch (Exception e) {
      return 0; // 삭제 실패 시 0 반환
    }
  }
}

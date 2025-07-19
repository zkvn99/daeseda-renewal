package com.experiment.daeseda_renewal.domain.clothes;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClothesServiceImpl implements ClothesService {

    private final ClothesRepository clothesRepository;

    @Override
    public List<Clothes> getAllClothes() {
        return clothesRepository.findAll();
    }

    @Override
    public Clothes createClothes(CreateClothesDTO createClothesDTO) {
        Clothes clothes = convertToEntity(createClothesDTO);
        Clothes savedClothes = clothesRepository.save(clothes);
        return savedClothes;
    }

    @Override
    public CreateClothesDTO getClothesById(Long clothesById) {
        return null;
    }

}

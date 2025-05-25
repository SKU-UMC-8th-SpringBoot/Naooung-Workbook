package com.umc.umcstudy.service.storeService;

import com.umc.umcstudy.converter.StoreConverter;
import com.umc.umcstudy.domain.entity.Region;
import com.umc.umcstudy.domain.entity.Store;
import com.umc.umcstudy.repository.RegionRepository;
import com.umc.umcstudy.repository.storeRepository.StoreRepository;
import com.umc.umcstudy.web.dto.store.StoreRequestDto;
import org.springframework.transaction.annotation.Transactional;

public class StoreCommandServiceImpl implements StoreCommandService {

  private StoreRepository storeRepository;

  private RegionRepository regionRepository;

  @Override
  @Transactional
  public Store addStore(Long regionId, StoreRequestDto.AddDto request) {

    Region region = regionRepository.findById(regionId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 지역입니다."));

    Store newStore = StoreConverter.toStore(region, request);

    return storeRepository.save(newStore);
  }
}

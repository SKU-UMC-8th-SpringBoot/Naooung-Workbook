package com.umc.umcstudy.converter;

import com.umc.umcstudy.domain.entity.Region;
import com.umc.umcstudy.domain.entity.Store;
import com.umc.umcstudy.web.dto.store.StoreRequestDto;
import com.umc.umcstudy.web.dto.store.StoreResponseDto;
import java.time.LocalDateTime;

public class StoreConverter {

  public static StoreResponseDto.AddStoreDTO toAddStoreDTO(Store store) {
    return StoreResponseDto.AddStoreDTO.builder()
        .storeId(store.getId())
        .createdAt(LocalDateTime.now())
        .build();
  }

  public static Store toStore(Region region, StoreRequestDto.AddDto request) {

    return Store.builder()
        .name(request.getName())
        .address(request.getAddress())
        .region(region)
        .build();
  }
}

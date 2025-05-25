package com.umc.umcstudy.service.storeService;

import com.umc.umcstudy.domain.entity.Store;
import com.umc.umcstudy.web.dto.store.StoreRequestDto;

public interface StoreCommandService {

  public Store addStore(Long storeId, StoreRequestDto.AddDto request);
}

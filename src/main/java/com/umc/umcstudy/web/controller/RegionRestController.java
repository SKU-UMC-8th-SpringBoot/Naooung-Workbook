package com.umc.umcstudy.web.controller;

import com.umc.umcstudy.apiPayload.ApiResponse;
import com.umc.umcstudy.converter.StoreConverter;
import com.umc.umcstudy.domain.entity.Store;
import com.umc.umcstudy.service.storeService.StoreCommandService;
import com.umc.umcstudy.web.dto.store.StoreRequestDto;
import com.umc.umcstudy.web.dto.store.StoreResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/regions")
public class RegionRestController {

  private final StoreCommandService storeCommandService;

  // 지역에 가게를 추가하는 API
  @PostMapping("/{regionId}/stores")
  public ApiResponse<StoreResponseDto.AddStoreDTO> add
      (@PathVariable Long regionId, @RequestBody @Valid StoreRequestDto.AddDto request) {

    Store store = storeCommandService.addStore(regionId, request);
    return ApiResponse.onSuccess(StoreConverter.toAddStoreDTO(store));
  }
}

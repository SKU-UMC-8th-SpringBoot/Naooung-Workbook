package com.umc.umcstudy.web.dto.store;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class StoreResponseDto {

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class AddStoreDTO {
    Long storeId;
    LocalDateTime createdAt;
  }
}

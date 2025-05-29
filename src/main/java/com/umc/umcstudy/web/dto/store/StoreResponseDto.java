package com.umc.umcstudy.web.dto.store;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ReviewPreViewListDTO {
    List<ReviewPreViewDTO> reviewList;
    Integer listSize;
    Integer totalPage;
    Long totalElements;
    Boolean isFirst;
    Boolean isLast;
  }

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ReviewPreViewDTO {
    String ownerNickname;
    Float score;
    String body;
    LocalDate createdAt;
  }
}

package com.umc.umcstudy.web.dto.review;

import com.umc.umcstudy.web.dto.mission.MissionResponseDto;
import com.umc.umcstudy.web.dto.mission.MissionResponseDto.MissionPreViewDTO;
import com.umc.umcstudy.web.dto.store.StoreResponseDto;
import com.umc.umcstudy.web.dto.store.StoreResponseDto.ReviewPreViewDTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReviewResponseDto {

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class AddResultDTO{
    Long reviewId;
    LocalDateTime createdAt;
  }

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class MyReviewListDTO {
    List<ReviewResponseDto.MyReviewDTO> reviewList;
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
  public static class MyReviewDTO {
    String ownerNickname;
    Float score;
    String body;
    LocalDate createdAt;
  }
}

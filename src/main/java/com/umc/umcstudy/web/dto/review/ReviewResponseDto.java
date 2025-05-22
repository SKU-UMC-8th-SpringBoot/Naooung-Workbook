package com.umc.umcstudy.web.dto.review;

import java.time.LocalDateTime;
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
}

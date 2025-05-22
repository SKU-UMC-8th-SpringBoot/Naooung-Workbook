package com.umc.umcstudy.web.dto.review;

import com.umc.umcstudy.domain.entity.Store;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class ReviewRequestDto {

  @Getter
  public static class AddDto {
    @Size(min = 5, max = 50)
    String title;

    @NotNull
    float score;
  }
}
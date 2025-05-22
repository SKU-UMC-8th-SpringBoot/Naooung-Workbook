package com.umc.umcstudy.converter;

import com.umc.umcstudy.domain.entity.Review;
import com.umc.umcstudy.domain.entity.Store;
import com.umc.umcstudy.web.dto.review.ReviewRequestDto;
import com.umc.umcstudy.web.dto.review.ReviewResponseDto;
import java.time.LocalDateTime;

public class ReviewConverter {

  public static ReviewResponseDto.AddResultDTO toAddResultDTO(Review review) {
    return ReviewResponseDto.AddResultDTO.builder()
        .reviewId(review.getId())
        .createdAt(LocalDateTime.now())
        .build();
  }

  public static Review toReview(Store store, ReviewRequestDto.AddDto request) {

    return Review.builder()
        .title(request.getTitle())
        .score(request.getScore())
        .store(store)
        .build();
  }
}

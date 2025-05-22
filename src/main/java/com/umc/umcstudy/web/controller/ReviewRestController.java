package com.umc.umcstudy.web.controller;

import com.umc.umcstudy.apiPayload.ApiResponse;
import com.umc.umcstudy.converter.ReviewConverter;
import com.umc.umcstudy.domain.entity.Review;
import com.umc.umcstudy.service.reviewService.ReviewCommandService;
import com.umc.umcstudy.validation.annotation.ExistStores;
import com.umc.umcstudy.web.dto.review.ReviewRequestDto;
import com.umc.umcstudy.web.dto.review.ReviewResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated // PathVariable로 받는 값을 ExistStores 어노테이션으로 검증하기 위해 사용
@RequestMapping("/stores")
public class ReviewRestController {

  private final ReviewCommandService reviewCommandService;

  @PostMapping("/{storeId}/reviews")
  public ApiResponse<ReviewResponseDto.AddResultDTO> add
      (@PathVariable @ExistStores Long storeId, @RequestBody @Valid ReviewRequestDto.AddDto request) {

    Review review = reviewCommandService.addReview(storeId, request);
    return ApiResponse.onSuccess(ReviewConverter.toAddResultDTO(review));
  }
}

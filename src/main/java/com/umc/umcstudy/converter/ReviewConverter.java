package com.umc.umcstudy.converter;

import com.umc.umcstudy.domain.entity.Review;
import com.umc.umcstudy.domain.entity.Store;
import com.umc.umcstudy.web.dto.review.ReviewRequestDto;
import com.umc.umcstudy.web.dto.review.ReviewResponseDto;
import com.umc.umcstudy.web.dto.review.ReviewResponseDto.MyReviewDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;

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

  public static ReviewResponseDto.MyReviewDTO myReviewDTO(Review review) {
    return ReviewResponseDto.MyReviewDTO.builder()
        .score(review.getScore())
        .body(review.getBody())
        .createdAt(review.getCreatedAt().toLocalDate())
        .build();
  }

  public static ReviewResponseDto.MyReviewListDTO myReviewListDTO
      (Page<Review> reviewList){

    List<MyReviewDTO> myReviewDTOList = reviewList.stream()
        .map(ReviewConverter::myReviewDTO).collect(Collectors.toList());

    return ReviewResponseDto.MyReviewListDTO.builder()
        .isLast(reviewList.isLast())
        .isFirst(reviewList.isFirst())
        .totalPage(reviewList.getTotalPages())
        .totalElements(reviewList.getTotalElements())
        .listSize(myReviewDTOList.size())
        .reviewList(myReviewDTOList)
        .build();
  }
}

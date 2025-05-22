package com.umc.umcstudy.service.reviewService;

import com.umc.umcstudy.domain.entity.Review;
import com.umc.umcstudy.domain.entity.Store;
import com.umc.umcstudy.web.dto.review.ReviewRequestDto;

public interface ReviewCommandService {

  public Review addReview(Long storeId, ReviewRequestDto.AddDto request);
}

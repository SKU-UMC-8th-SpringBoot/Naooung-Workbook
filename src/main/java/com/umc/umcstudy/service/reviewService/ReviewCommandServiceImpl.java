package com.umc.umcstudy.service.reviewService;

import com.umc.umcstudy.converter.ReviewConverter;
import com.umc.umcstudy.domain.entity.Review;
import com.umc.umcstudy.domain.entity.Store;
import com.umc.umcstudy.repository.ReviewRepository;
import com.umc.umcstudy.repository.storeRepository.StoreRepository;
import com.umc.umcstudy.web.dto.review.ReviewRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

  private final ReviewRepository reviewRepository;

  private final StoreRepository storeRepository;

  @Override
  @Transactional
  public Review addReview(Long storeId, ReviewRequestDto.AddDto request) {

    Store store = storeRepository.findById(storeId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));

    Review newReview = ReviewConverter.toReview(store, request);

    return reviewRepository.save(newReview);
  }
}

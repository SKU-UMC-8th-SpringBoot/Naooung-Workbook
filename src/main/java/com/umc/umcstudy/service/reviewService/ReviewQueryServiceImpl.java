package com.umc.umcstudy.service.reviewService;

import com.umc.umcstudy.domain.entity.Member;
import com.umc.umcstudy.domain.entity.Review;
import com.umc.umcstudy.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {

  private final ReviewRepository reviewRepository;

  public Page<Review> getReviewList(Member member, Integer page) {

    Page<Review> ReviewPage = reviewRepository
        .findAllByMember(member, PageRequest.of(page - 1, 10));

    return ReviewPage;
  }
}

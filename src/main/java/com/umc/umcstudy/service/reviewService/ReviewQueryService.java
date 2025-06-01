package com.umc.umcstudy.service.reviewService;

import com.umc.umcstudy.domain.entity.Member;
import com.umc.umcstudy.domain.entity.Review;
import org.springframework.data.domain.Page;

public interface ReviewQueryService {

  Page<Review> getReviewList(Member member, Integer page);
}

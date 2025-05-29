package com.umc.umcstudy.service.storeService;

import com.umc.umcstudy.domain.entity.Review;
import com.umc.umcstudy.domain.entity.Store;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface StoreQueryService {

  Optional<Store> findStore(Long id);
  List<Store> findStoresByNameAndScore(String name, Float score);

  Page<Review> getReviewList(Long StoreId, Integer page);
}

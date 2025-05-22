package com.umc.umcstudy.service.storeService;

import com.umc.umcstudy.domain.entity.Store;
import java.util.List;
import java.util.Optional;

public interface StoreQueryService {

  Optional<Store> findStore(Long id);
  List<Store> findStoresByNameAndScore(String name, Float score);
}

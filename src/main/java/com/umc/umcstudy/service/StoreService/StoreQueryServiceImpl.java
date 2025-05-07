package com.umc.umcstudy.service.StoreService;

import com.umc.umcstudy.domain.entity.Store;
import com.umc.umcstudy.repository.storeRepository.StoreRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService{

  private final StoreRepository storeRepository;

  @Override
  public Optional<Store> findStore(Long id) {
    return storeRepository.findById(id);
  }

  @Override
  public List<Store> findStoresByNameAndScore(String name, Float score) {
    List<Store> filteredStores = storeRepository.dynamicQueryWithBooleanBuilder(name, score);

    filteredStores.forEach(store -> System.out.println("Store: " + store));

    return filteredStores;
  }
}

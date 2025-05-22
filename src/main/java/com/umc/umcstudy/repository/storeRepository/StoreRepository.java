package com.umc.umcstudy.repository.storeRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.umc.umcstudy.domain.entity.Store;


public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {

}

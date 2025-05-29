package com.umc.umcstudy.repository;

import com.umc.umcstudy.domain.entity.Mission;
import com.umc.umcstudy.domain.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {

  Page<Mission> findAllByStore(Store store, PageRequest pageRequest);
}

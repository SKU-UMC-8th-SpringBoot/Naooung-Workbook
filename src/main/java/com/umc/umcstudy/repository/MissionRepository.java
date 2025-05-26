package com.umc.umcstudy.repository;

import com.umc.umcstudy.domain.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {

}

package com.umc.umcstudy.repository;

import com.umc.umcstudy.domain.entity.Member;
import com.umc.umcstudy.domain.entity.Mission;
import com.umc.umcstudy.domain.enums.MissionStatus;
import com.umc.umcstudy.domain.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {


  @Query("SELECT mm.mission FROM MemberMission mm WHERE mm.member = :member AND mm.status = :status")
  Page<Mission> findAllByMemberAndStatus(
      Member member, MissionStatus status, PageRequest pageRequest);
}

package com.umc.umcstudy.service.memberMissionService;

import com.umc.umcstudy.domain.entity.Member;
import com.umc.umcstudy.domain.entity.Mission;
import com.umc.umcstudy.domain.enums.MissionStatus;
import com.umc.umcstudy.domain.mapping.MemberMission;
import com.umc.umcstudy.repository.MemberMissionRepository;
import com.umc.umcstudy.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {

  private final MemberMissionRepository memberMissionRepository;
  private final MissionRepository missionRepository;

  public Page<Mission> getMissionsByStatus(Member member, MissionStatus status, Integer page) {

    Page<Mission> MissionPage = memberMissionRepository.findAllByMemberAndStatus
        (member, status, PageRequest.of(page - 1, 10));

    return MissionPage;
  }
}

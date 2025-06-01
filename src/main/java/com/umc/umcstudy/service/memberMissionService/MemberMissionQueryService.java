package com.umc.umcstudy.service.memberMissionService;

import com.umc.umcstudy.domain.entity.Member;
import com.umc.umcstudy.domain.entity.Mission;
import com.umc.umcstudy.domain.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberMissionQueryService {

  Page<Mission> getMissionsByStatus(Member member, MissionStatus status, Integer Page);
}

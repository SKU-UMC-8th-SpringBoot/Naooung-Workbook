package com.umc.umcstudy.service.memberMissionService;

import com.umc.umcstudy.domain.mapping.MemberMission;
import com.umc.umcstudy.web.dto.memberMission.MemberMissionRequestDTO;

public interface MemberMissionCommandService {

  public MemberMission addMemberMission(Long memberId, Long missionId, MemberMissionRequestDTO.AddDto request);
}

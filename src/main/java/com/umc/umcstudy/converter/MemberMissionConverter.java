package com.umc.umcstudy.converter;

import com.umc.umcstudy.domain.entity.Member;
import com.umc.umcstudy.domain.entity.Mission;
import com.umc.umcstudy.domain.mapping.MemberMission;
import com.umc.umcstudy.web.dto.memberMission.MemberMissionRequestDTO;
import com.umc.umcstudy.web.dto.memberMission.MemberMissionResponseDTO;
import java.time.LocalDateTime;

public class MemberMissionConverter {

  public static MemberMissionResponseDTO.AddMemberMissionDTO toAddMemberMissionDTO
      (MemberMission memberMission) {
    return MemberMissionResponseDTO.AddMemberMissionDTO.builder()
        .memberMissionId(memberMission.getId())
        .createdAt(LocalDateTime.now())
        .build();
  }

  public static MemberMission toMemberMission(Member member, Mission mission, MemberMissionRequestDTO.AddDto request) {
    return MemberMission.builder()
        .status(request.getStatus())
        .member(member)
        .mission(mission)
        .build();
  }
}

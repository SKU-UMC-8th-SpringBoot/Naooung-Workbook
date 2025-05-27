package com.umc.umcstudy.service.memberMission;

import com.umc.umcstudy.converter.MemberMissionConverter;
import com.umc.umcstudy.domain.entity.Member;
import com.umc.umcstudy.domain.entity.Mission;
import com.umc.umcstudy.domain.mapping.MemberMission;
import com.umc.umcstudy.repository.MemberMissionRepository;
import com.umc.umcstudy.repository.MemberRepository;
import com.umc.umcstudy.repository.MissionRepository;
import com.umc.umcstudy.web.dto.memberMission.MemberMissionRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {

  private final MemberMissionRepository memberMissionRepository;

  private final MemberRepository memberRepository;
  private final MissionRepository missionRepository;

  public MemberMission addMemberMission(Long memberId, Long missionId, MemberMissionRequestDTO.AddDto request) {

    Member member = memberRepository.findById(memberId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

    Mission mission = missionRepository.findById(missionId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 미션입니다."));

    MemberMission newMemberMission = MemberMissionConverter.toMemberMission(member, mission, request);

    return memberMissionRepository.save(newMemberMission);
  }
}

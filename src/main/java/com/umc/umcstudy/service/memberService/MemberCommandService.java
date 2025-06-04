package com.umc.umcstudy.service.memberService;

import com.umc.umcstudy.domain.entity.Member;
import com.umc.umcstudy.web.dto.member.MemberRequestDTO;
import com.umc.umcstudy.web.dto.member.MemberResponseDTO;

public interface MemberCommandService {

  public Member joinMember(MemberRequestDTO.JoinDto request);

  MemberResponseDTO.LoginResultDTO loginMember(MemberRequestDTO.LoginRequestDTO request);
}

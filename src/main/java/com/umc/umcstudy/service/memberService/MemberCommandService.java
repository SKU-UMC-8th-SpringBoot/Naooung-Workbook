package com.umc.umcstudy.service.memberService;

import com.umc.umcstudy.domain.entity.Member;
import com.umc.umcstudy.web.dto.member.MemberRequestDTO;

public interface MemberCommandService {

  public Member joinMember(MemberRequestDTO.JoinDto request);
}

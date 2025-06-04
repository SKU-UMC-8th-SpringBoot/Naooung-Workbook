package com.umc.umcstudy.service.memberService;

import com.umc.umcstudy.web.dto.member.MemberResponseDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface MemberQueryService {

  MemberResponseDTO.MemberInfoDTO getMemberInfo(HttpServletRequest request);
}

package com.umc.umcstudy.service.memberService;

import com.umc.umcstudy.apiPayload.code.status.ErrorStatus;
import com.umc.umcstudy.apiPayload.exception.handler.MemberHandler;
import com.umc.umcstudy.config.jwt.JwtTokenProvider;
import com.umc.umcstudy.converter.MemberConverter;
import com.umc.umcstudy.domain.entity.Member;
import com.umc.umcstudy.repository.MemberRepository;
import com.umc.umcstudy.web.dto.member.MemberResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {

  private final MemberRepository memberRepository;
  private final JwtTokenProvider jwtTokenProvider;

  @Override
  @Transactional(readOnly = true)
  public MemberResponseDTO.MemberInfoDTO getMemberInfo(HttpServletRequest request){
    Authentication authentication = jwtTokenProvider.extractAuthentication(request);
    String email = authentication.getName();

    Member member = memberRepository.findByEmail(email)
        .orElseThrow(()-> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
    return MemberConverter.toMemberInfoDTO(member);
  }
}

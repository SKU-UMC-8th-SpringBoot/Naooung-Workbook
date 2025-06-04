package com.umc.umcstudy.web.controller;

import com.umc.umcstudy.apiPayload.ApiResponse;
import com.umc.umcstudy.converter.MemberConverter;
import com.umc.umcstudy.domain.entity.Member;
import com.umc.umcstudy.service.memberService.MemberCommandService;
import com.umc.umcstudy.service.memberService.MemberQueryService;
import com.umc.umcstudy.web.dto.member.MemberRequestDTO;
import com.umc.umcstudy.web.dto.member.MemberResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {

  private final MemberCommandService memberCommandService;
  private final MemberQueryService memberQueryService;

  @PostMapping("/join")
  public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request){
    Member member = memberCommandService.joinMember(request);
    return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
  }

  @PostMapping("/login")
  @Operation(summary = "유저 로그인 API",description = "유저가 로그인하는 API입니다.")
  public ApiResponse<MemberResponseDTO.LoginResultDTO> login(@RequestBody @Valid MemberRequestDTO.LoginRequestDTO request) {
    return ApiResponse.onSuccess(memberCommandService.loginMember(request));
  }

  @GetMapping("/info")
  @Operation(summary = "유저 내 정보 조회 API - 인증 필요",
      description = "유저가 내 정보를 조회하는 API입니다.",
      security = { @SecurityRequirement(name = "JWT TOKEN") }
  )

  public ApiResponse<MemberResponseDTO.MemberInfoDTO> getMyInfo(HttpServletRequest request) {
    return ApiResponse.onSuccess(memberQueryService.getMemberInfo(request));
  }
}

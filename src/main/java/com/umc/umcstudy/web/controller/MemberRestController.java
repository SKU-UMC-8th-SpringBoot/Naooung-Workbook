package com.umc.umcstudy.web.controller;

import com.umc.umcstudy.apiPayload.ApiResponse;
import com.umc.umcstudy.converter.MemberConverter;
import com.umc.umcstudy.domain.entity.Member;
import com.umc.umcstudy.service.memberService.MemberCommandService;
import com.umc.umcstudy.web.dto.member.MemberRequestDTO;
import com.umc.umcstudy.web.dto.member.MemberResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {

  private final MemberCommandService memberCommandService;

  @PostMapping("/")
  public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request){
    Member member = memberCommandService.joinMember(request);
    return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
  }
}

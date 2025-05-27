package com.umc.umcstudy.web.controller;

import com.umc.umcstudy.apiPayload.ApiResponse;
import com.umc.umcstudy.converter.MemberMissionConverter;
import com.umc.umcstudy.domain.mapping.MemberMission;
import com.umc.umcstudy.service.memberMission.MemberMissionCommandService;
import com.umc.umcstudy.validation.annotation.ExistMission;
import com.umc.umcstudy.web.dto.memberMission.MemberMissionRequestDTO;
import com.umc.umcstudy.web.dto.memberMission.MemberMissionResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/missions")
public class MissionRestController {

  private final MemberMissionCommandService memberMissionCommandService;

  // 가게의 미션을 도전중인 미션에 추가하는 API
  @PostMapping("/{missionId}/challenge")
  public ApiResponse<MemberMissionResponseDTO.AddMemberMissionDTO> add
    (@PathVariable @ExistMission Long missionId, @RequestBody @Valid MemberMissionRequestDTO.AddDto request) {

    MemberMission memberMission = memberMissionCommandService.addMemberMission(request.getMemberId(), missionId, request);
    return ApiResponse.onSuccess(MemberMissionConverter.toAddMemberMissionDTO(memberMission));
  }
}

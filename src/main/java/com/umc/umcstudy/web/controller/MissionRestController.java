package com.umc.umcstudy.web.controller;

import com.umc.umcstudy.apiPayload.ApiResponse;
import com.umc.umcstudy.converter.MemberMissionConverter;
import com.umc.umcstudy.converter.MissionConverter;
import com.umc.umcstudy.domain.entity.Member;
import com.umc.umcstudy.domain.entity.Mission;
import com.umc.umcstudy.domain.enums.MissionStatus;
import com.umc.umcstudy.domain.mapping.MemberMission;
import com.umc.umcstudy.repository.MemberRepository;
import com.umc.umcstudy.service.memberMissionService.MemberMissionCommandService;
import com.umc.umcstudy.service.memberMissionService.MemberMissionQueryService;
import com.umc.umcstudy.validation.annotation.ExistMission;
import com.umc.umcstudy.validation.annotation.ValidPage;
import com.umc.umcstudy.web.dto.memberMission.MemberMissionRequestDTO;
import com.umc.umcstudy.web.dto.memberMission.MemberMissionResponseDTO;
import com.umc.umcstudy.web.dto.mission.MissionResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/missions")
public class MissionRestController {

  private final MemberMissionCommandService memberMissionCommandService;
  private final MemberMissionQueryService memberMissionQueryService;
  private final MemberRepository memberRepository;

  // 가게의 미션을 도전중인 미션에 추가하는 API
  @PostMapping("/{missionId}/challenge")
  public ApiResponse<MemberMissionResponseDTO.AddMemberMissionDTO> add
    (@PathVariable @ExistMission Long missionId, @RequestBody @Valid MemberMissionRequestDTO.AddDto request) {

    MemberMission memberMission = memberMissionCommandService.addMemberMission(request.getMemberId(), missionId, request);
    return ApiResponse.onSuccess(MemberMissionConverter.toAddMemberMissionDTO(memberMission));
  }

  // 내가 진행중인 미션 목록 조회 API
  @GetMapping("")
  @Operation(summary = "내가 진행중인 미션 목록 조회 API", description = "내가 진행중인 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
  @ApiResponses({
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
  })
  @Parameters({
      @Parameter(name = "status", description = "도전중 또는 도전완료")
  })
  public ApiResponse<MissionResponseDto.MissionPreViewListDTO> getMissionList
      (@RequestParam(name = "status") MissionStatus status,
       @ValidPage @RequestParam(name = "page") Integer page) {
    Member fakeMember = memberRepository.getById(1L);

    var missionList = memberMissionQueryService.getMissionsByStatus(fakeMember, status, page);
    return ApiResponse.onSuccess(MissionConverter.missionPreViewListDTO(missionList));
  }
}

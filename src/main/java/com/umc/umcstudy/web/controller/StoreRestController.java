package com.umc.umcstudy.web.controller;

import com.umc.umcstudy.apiPayload.ApiResponse;
import com.umc.umcstudy.converter.MissionConverter;
import com.umc.umcstudy.converter.ReviewConverter;
import com.umc.umcstudy.converter.StoreConverter;
import com.umc.umcstudy.domain.entity.Mission;
import com.umc.umcstudy.domain.entity.Review;
import com.umc.umcstudy.service.missionService.MissionCommandService;
import com.umc.umcstudy.service.reviewService.ReviewCommandService;
import com.umc.umcstudy.service.storeService.StoreQueryService;
import com.umc.umcstudy.validation.annotation.ExistStores;
import com.umc.umcstudy.web.dto.mission.MissionRequestDto;
import com.umc.umcstudy.web.dto.mission.MissionResponseDto;
import com.umc.umcstudy.web.dto.review.ReviewRequestDto;
import com.umc.umcstudy.web.dto.review.ReviewResponseDto;
import com.umc.umcstudy.web.dto.store.StoreResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@Validated // PathVariable로 받는 값을 ExistStores 어노테이션으로 검증하기 위해 사용
@RequestMapping("/stores")
public class StoreRestController {

  private final StoreQueryService storeQueryService;

  private final ReviewCommandService reviewCommandService;
  private final MissionCommandService missionCommandService;

  // 가게에 리뷰를 추가하는 API
  @PostMapping("/{storeId}/reviews")
  public ApiResponse<ReviewResponseDto.AddResultDTO> add
      (@PathVariable @ExistStores Long storeId, @RequestBody @Valid ReviewRequestDto.AddDto request) {

    Review review = reviewCommandService.addReview(storeId, request);
    return ApiResponse.onSuccess(ReviewConverter.toAddResultDTO(review));
  }

  // 가게에 미션을 추가하는 API
  @PostMapping("/{storeId}/missions")
  public ApiResponse<MissionResponseDto.AddMissionDTO> add
      (@PathVariable @ExistStores Long storeId, @RequestBody @Valid MissionRequestDto.AddDto request) {

    Mission mission = missionCommandService.addMission(storeId, request);
    return ApiResponse.onSuccess(MissionConverter.toAddMissionDTO(mission));
  }

  @GetMapping("/{storeId}/reviews")
  @Operation(summary = "특정 가게의 리뷰 목록 조회 API", description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
  @ApiResponses({
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
  })
  @Parameters({
      @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
  })
  public ApiResponse<StoreResponseDto.ReviewPreViewListDTO> getReviewList(
      @ExistStores @PathVariable(name = "storeId") Long storeId,
      @RequestParam(name = "page") Integer page) {

    var reviewList = storeQueryService.getReviewList(storeId, page);
    return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(reviewList));
  }
}

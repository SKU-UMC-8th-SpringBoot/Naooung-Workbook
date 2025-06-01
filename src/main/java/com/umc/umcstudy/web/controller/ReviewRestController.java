package com.umc.umcstudy.web.controller;

import com.umc.umcstudy.apiPayload.ApiResponse;
import com.umc.umcstudy.converter.ReviewConverter;
import com.umc.umcstudy.converter.StoreConverter;
import com.umc.umcstudy.domain.entity.Member;
import com.umc.umcstudy.repository.MemberRepository;
import com.umc.umcstudy.service.reviewService.ReviewQueryService;
import com.umc.umcstudy.validation.annotation.ExistStores;
import com.umc.umcstudy.validation.annotation.ValidPage;
import com.umc.umcstudy.web.dto.review.ReviewResponseDto;
import com.umc.umcstudy.web.dto.store.StoreResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewRestController {

  private final MemberRepository memberRepository;
  private final ReviewQueryService reviewQueryService;

  // 내가 작성한 리뷰 목록 조회 API
  @GetMapping("")
  @Operation(summary = "내가 작성한 리뷰 목록 조회 API", description = "내가 작성한 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
  @ApiResponses({
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
  })
  public ApiResponse<ReviewResponseDto.MyReviewListDTO> getReviewList
  (@ValidPage @RequestParam(name = "page") Integer page) {

    Member fakeMember = memberRepository.getById(1L);

    var reviewList = reviewQueryService.getReviewList(fakeMember, page);
    return ApiResponse.onSuccess(ReviewConverter.myReviewListDTO(reviewList));
  }
}

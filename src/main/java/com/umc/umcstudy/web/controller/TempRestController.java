package com.umc.umcstudy.web.controller;

import com.umc.umcstudy.apiPayload.ApiResponse;
import com.umc.umcstudy.converter.TempConverter;
import com.umc.umcstudy.service.TempService.TempQueryService;
import com.umc.umcstudy.web.dto.temp.TempResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempRestController {

  private final TempQueryService tempQueryService;

  @GetMapping("/test")
  public ApiResponse<TempResponse.TempTestDTO> testAPI(){

    return ApiResponse.onSuccess(TempConverter.toTempTestDTO());
  }

  @GetMapping("/exception")
  public ApiResponse<TempResponse.TempExceptionDTO> exceptionAPI(@RequestParam Integer flag){
    tempQueryService.CheckFlag(flag);
    return ApiResponse.onSuccess(TempConverter.toTempExceptionDTO(flag));
  }
}
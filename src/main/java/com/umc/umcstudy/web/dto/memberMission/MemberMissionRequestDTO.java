package com.umc.umcstudy.web.dto.memberMission;

import com.umc.umcstudy.domain.enums.MissionStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class MemberMissionRequestDTO {

  @Getter
  public static class AddDto {

    // 임시로 JWT 인증 대신 필드 사용
    @NotNull
    Long memberId;

    @NotNull
    MissionStatus status;
  }
}

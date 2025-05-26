package com.umc.umcstudy.web.dto.mission;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MissionResponseDto {

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class AddMissionDTO {
    Long missionId;
    LocalDateTime createAt;
  }
}

package com.umc.umcstudy.web.dto.memberMission;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberMissionResponseDTO {

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class AddMemberMissionDTO {
    Long memberMissionId;
    LocalDateTime createdAt;
  }
}

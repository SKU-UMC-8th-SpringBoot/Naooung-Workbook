package com.umc.umcstudy.web.dto.mission;

import com.umc.umcstudy.web.dto.store.StoreResponseDto;
import com.umc.umcstudy.web.dto.store.StoreResponseDto.MissionPreViewDTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class MissionPreViewListDTO {
    List<MissionResponseDto.MissionPreViewDTO> missionList;
    Integer listSize;
    Integer totalPage;
    Long totalElements;
    Boolean isFirst;
    Boolean isLast;
  }

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class MissionPreViewDTO {
    Integer reward;
    LocalDate deadline;
    String missionSpec;
    LocalDate createdAt;
  }
}

package com.umc.umcstudy.converter;

import com.umc.umcstudy.domain.entity.Mission;
import com.umc.umcstudy.domain.entity.Store;
import com.umc.umcstudy.web.dto.mission.MissionRequestDto;
import com.umc.umcstudy.web.dto.mission.MissionResponseDto;
import java.time.LocalDateTime;

public class MissionConverter {

  public static MissionResponseDto.AddMissionDTO toAddMissionDTO(Mission mission) {
    return MissionResponseDto.AddMissionDTO.builder()
        .missionId(mission.getId())
        .createAt(LocalDateTime.now())
        .build();
  }

  public static Mission toMission(Store store, MissionRequestDto.AddDto request) {
    return Mission.builder()
        .reward(request.getReward())
        .deadline(request.getDeadline())
        .missionSpec(request.getMissionSpec())
        .store(store)
        .build();
  }
}

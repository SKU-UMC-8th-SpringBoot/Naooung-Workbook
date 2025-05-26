package com.umc.umcstudy.service.missionService;

import com.umc.umcstudy.domain.entity.Mission;
import com.umc.umcstudy.web.dto.mission.MissionRequestDto;

public interface MissionCommandService {

  public Mission addMission(Long missionId, MissionRequestDto.AddDto request);
}

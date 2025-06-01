package com.umc.umcstudy.converter;

import com.umc.umcstudy.domain.entity.Mission;
import com.umc.umcstudy.domain.entity.Store;
import com.umc.umcstudy.web.dto.mission.MissionRequestDto;
import com.umc.umcstudy.web.dto.mission.MissionResponseDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;

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

  public static MissionResponseDto.MissionPreViewDTO missionPreViewDTO(Mission mission){
    return MissionResponseDto.MissionPreViewDTO.builder()
        .reward(mission.getReward())
        .deadline(mission.getDeadline())
        .missionSpec(mission.getMissionSpec())
        .createdAt(mission.getCreatedAt().toLocalDate())
        .build();
  }

  public static MissionResponseDto.MissionPreViewListDTO missionPreViewListDTO
      (Page<Mission> missionList){

    List<MissionResponseDto.MissionPreViewDTO> missionPreViewDTOList = missionList.stream()
        .map(MissionConverter::missionPreViewDTO).collect(Collectors.toList());

    return MissionResponseDto.MissionPreViewListDTO.builder()
        .isLast(missionList.isLast())
        .isFirst(missionList.isFirst())
        .totalPage(missionList.getTotalPages())
        .totalElements(missionList.getTotalElements())
        .listSize(missionPreViewDTOList.size())
        .missionList(missionPreViewDTOList)
        .build();
  }
}

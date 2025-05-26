package com.umc.umcstudy.service.missionService;

import com.umc.umcstudy.converter.MissionConverter;
import com.umc.umcstudy.domain.entity.Mission;
import com.umc.umcstudy.domain.entity.Store;
import com.umc.umcstudy.repository.MissionRepository;
import com.umc.umcstudy.repository.storeRepository.StoreRepository;
import com.umc.umcstudy.web.dto.mission.MissionRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

  private final MissionRepository missionRepository;

  private final StoreRepository storeRepository;

  @Override
  @Transactional
  public Mission addMission(Long storeId, MissionRequestDto.AddDto request) {

    Store store = storeRepository.findById(storeId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));

    Mission newMission = MissionConverter.toMission(store, request);

    return missionRepository.save(newMission);
  }
}

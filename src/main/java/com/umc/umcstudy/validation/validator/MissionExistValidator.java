package com.umc.umcstudy.validation.validator;

import com.umc.umcstudy.repository.MissionRepository;
import com.umc.umcstudy.validation.annotation.ExistMission;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MissionExistValidator implements ConstraintValidator<ExistMission, Long> {

  private final MissionRepository missionRepository;

  @Override
  public boolean isValid(Long missionId, ConstraintValidatorContext context) {
    if (missionId == null) return false;
    return missionRepository.existsById(missionId);
  }
}
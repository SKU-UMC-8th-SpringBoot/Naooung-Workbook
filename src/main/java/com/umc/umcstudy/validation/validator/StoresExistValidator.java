package com.umc.umcstudy.validation.validator;

import com.umc.umcstudy.repository.storeRepository.StoreRepository;
import com.umc.umcstudy.validation.annotation.ExistStores;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoresExistValidator implements ConstraintValidator<ExistStores, Long> {

  private final StoreRepository storeRepository;

  @Override
  public boolean isValid(Long storeId, ConstraintValidatorContext context) {
    if (storeId == null)
      return false;
    return storeRepository.existsById(storeId);
  }
}

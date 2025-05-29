package com.umc.umcstudy.validation.validator;

import com.umc.umcstudy.validation.annotation.ValidPage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class PageValidator implements ConstraintValidator<ValidPage, Integer> {

  @Override
  public boolean isValid(Integer page, ConstraintValidatorContext context) {
    return page != null && page >= 1;
  }
}

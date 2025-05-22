package com.umc.umcstudy.validation.validator;

import com.umc.umcstudy.apiPayload.code.status.ErrorStatus;
import com.umc.umcstudy.service.memberService.MemberCommandServiceImpl;
import com.umc.umcstudy.validation.annotation.ExistCategories;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoriesExistValidator implements ConstraintValidator<ExistCategories, List<Long>> {

  private final MemberCommandServiceImpl memberCommandService;

  @Override
  public void initialize(ExistCategories constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(List<Long> values, ConstraintValidatorContext context) {
    boolean isValid = memberCommandService.doAllCategoriesExist(values);

    if (!isValid) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(ErrorStatus.FOOD_CATEGORY_NOT_FOUND.getMessage())
          .addConstraintViolation();
    }

    return isValid;
  }
}

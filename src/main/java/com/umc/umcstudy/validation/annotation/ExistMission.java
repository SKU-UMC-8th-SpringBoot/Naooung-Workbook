package com.umc.umcstudy.validation.annotation;

import com.umc.umcstudy.validation.validator.MissionExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MissionExistValidator.class)
public @interface ExistMission {

  String message() default "해당하는 미션이 존재하지 않습니다.";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}

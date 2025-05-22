package com.umc.umcstudy.validation.annotation;

import com.umc.umcstudy.validation.validator.StoresExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = StoresExistValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistStores {

  String message() default "존재하지 않는 가게입니다.";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}

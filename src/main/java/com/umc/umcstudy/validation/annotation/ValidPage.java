package com.umc.umcstudy.validation.annotation;

import com.umc.umcstudy.validation.validator.PageValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = PageValidator.class)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPage {

  String message() default "page는 1 이상의 정수여야 합니다.";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}

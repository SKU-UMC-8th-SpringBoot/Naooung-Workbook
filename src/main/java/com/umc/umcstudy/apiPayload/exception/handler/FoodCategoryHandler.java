package com.umc.umcstudy.apiPayload.exception.handler;

import com.umc.umcstudy.apiPayload.code.BaseErrorCode;

public class FoodCategoryHandler extends GeneralException {
  public FoodCategoryHandler(BaseErrorCode errorCode) {
    super(errorCode);
  }
}

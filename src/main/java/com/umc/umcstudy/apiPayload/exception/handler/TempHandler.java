package com.umc.umcstudy.apiPayload.exception.handler;

import com.umc.umcstudy.apiPayload.code.BaseErrorCode;

public class TempHandler extends GeneralException {

  public TempHandler(BaseErrorCode errorCode) {
    super(errorCode);
  }
}

package com.umc.umcstudy.apiPayload.exception.handler;

import com.umc.umcstudy.apiPayload.code.BaseErrorCode;

public class MemberHandler extends GeneralException {

  public MemberHandler(BaseErrorCode errorCode) {
    super(errorCode);
  }
}

package com.umc.umcstudy.apiPayload.exception.handler;

import com.umc.umcstudy.apiPayload.code.BaseErrorCode;
import com.umc.umcstudy.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

  private BaseErrorCode code;

  public ErrorReasonDTO getErrorReason() {
    return this.code.getReason();
  }

  public ErrorReasonDTO getErrorReasonHttpStatus(){
    return this.code.getReasonHttpStatus();
  }
}

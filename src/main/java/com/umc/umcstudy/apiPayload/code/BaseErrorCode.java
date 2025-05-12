package com.umc.umcstudy.apiPayload.code;

public interface BaseErrorCode {

  ErrorReasonDTO getReason();

  ErrorReasonDTO getReasonHttpStatus();
}

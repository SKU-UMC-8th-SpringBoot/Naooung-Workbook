package com.umc.umcstudy.service.TempService;

import com.umc.umcstudy.apiPayload.code.status.ErrorStatus;
import com.umc.umcstudy.apiPayload.exception.handler.TempHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TempQueryServiceImpl implements TempQueryService{

  @Override
  public void CheckFlag(Integer flag) {
    if (flag == 1)
      throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);
  }
}

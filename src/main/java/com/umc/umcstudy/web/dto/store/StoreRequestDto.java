package com.umc.umcstudy.web.dto.store;

import jakarta.validation.constraints.Size;
import lombok.Getter;

public class StoreRequestDto {

  @Getter
  public static class AddDto {

    @Size(min = 5, max = 50)
    String name;

    @Size(min = 5, max = 50)
    String address;
  }
}

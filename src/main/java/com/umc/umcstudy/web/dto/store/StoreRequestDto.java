package com.umc.umcstudy.web.dto.store;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class StoreRequestDto {

  @Getter
  public static class AddDto {

    @NotNull
    @Size(min = 5, max = 50)
    @Schema(description = "가게 이름")
    String name;

    @NotNull
    @Size(min = 5, max = 50)
    @Schema(description = "가게 주소")
    String address;
  }
}

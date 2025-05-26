package com.umc.umcstudy.web.dto.mission;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;

public class MissionRequestDto {

  @Getter
  public static class AddDto {

    @NotNull
    Integer reward;

    @NotNull
    LocalDate deadline;

    @NotBlank
    @Size(min=5, max=100)
    String missionSpec;
  }
}

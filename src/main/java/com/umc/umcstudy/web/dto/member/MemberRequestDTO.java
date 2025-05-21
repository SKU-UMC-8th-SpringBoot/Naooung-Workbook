package com.umc.umcstudy.web.dto.member;

import java.util.List;
import lombok.Getter;

public class MemberRequestDTO {

  @Getter
  public static class JoinDto{
    String name;
    Integer gender;
    Integer birthYear;
    Integer birthMonth;
    Integer birthDay;
    String address;
    String specAddress;
    List<Long> preferCategory;
  }
}

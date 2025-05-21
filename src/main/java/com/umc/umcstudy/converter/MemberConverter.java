package com.umc.umcstudy.converter;

import com.umc.umcstudy.domain.entity.Member;
import com.umc.umcstudy.domain.enums.Gender;
import com.umc.umcstudy.web.dto.member.MemberRequestDTO;
import com.umc.umcstudy.web.dto.member.MemberResponseDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemberConverter {

  public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){
    return MemberResponseDTO.JoinResultDTO.builder()
        .memberId(member.getId())
        .createdAt(LocalDateTime.now())
        .build();
  }

  public static Member toMember(MemberRequestDTO.JoinDto request){

    Gender gender = null;

    switch (request.getGender()){
      case 1:
        gender = Gender.MALE;
        break;
      case 2:
        gender = Gender.FEMALE;
        break;
      case 3:
        gender = Gender.NONE;
        break;
    }

    return Member.builder()
        .address(request.getAddress())
        .specAddress(request.getSpecAddress())
        .gender(gender)
        .name(request.getName())
        .memberPreferList(new ArrayList<>())
        .address(request.getAddress())
        .build();
  }
}

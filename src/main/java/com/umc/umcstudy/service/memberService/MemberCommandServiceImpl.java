package com.umc.umcstudy.service.memberService;

import com.umc.umcstudy.apiPayload.code.status.ErrorStatus;
import com.umc.umcstudy.apiPayload.exception.handler.FoodCategoryHandler;
import com.umc.umcstudy.converter.MemberConverter;
import com.umc.umcstudy.converter.MemberPreferConverter;
import com.umc.umcstudy.domain.entity.FoodCategory;
import com.umc.umcstudy.domain.entity.Member;
import com.umc.umcstudy.domain.mapping.MemberPrefer;
import com.umc.umcstudy.repository.FoodCategoryRepository;
import com.umc.umcstudy.repository.MemberRepository;
import com.umc.umcstudy.web.dto.member.MemberRequestDTO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

  private final MemberRepository memberRepository;

  private final FoodCategoryRepository foodCategoryRepository;

  @Override
  @Transactional
  public Member joinMember(MemberRequestDTO.JoinDto request) {

    Member newMember = MemberConverter.toMember(request);
    List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
        .map(category -> {
          return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
        }).collect(Collectors.toList());

    List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

    memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

    return memberRepository.save(newMember);
  }
}

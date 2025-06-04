package com.umc.umcstudy.service.memberService;

import com.umc.umcstudy.apiPayload.code.status.ErrorStatus;
import com.umc.umcstudy.apiPayload.exception.handler.FoodCategoryHandler;
import com.umc.umcstudy.apiPayload.exception.handler.MemberHandler;
import com.umc.umcstudy.config.jwt.JwtTokenProvider;
import com.umc.umcstudy.converter.MemberConverter;
import com.umc.umcstudy.converter.MemberPreferConverter;
import com.umc.umcstudy.domain.entity.FoodCategory;
import com.umc.umcstudy.domain.entity.Member;
import com.umc.umcstudy.domain.mapping.MemberPrefer;
import com.umc.umcstudy.repository.FoodCategoryRepository;
import com.umc.umcstudy.repository.MemberRepository;
import com.umc.umcstudy.web.dto.member.MemberRequestDTO;
import com.umc.umcstudy.web.dto.member.MemberResponseDTO;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

  private final MemberRepository memberRepository;

  private final FoodCategoryRepository foodCategoryRepository;

  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;

  @Override
  @Transactional
  public Member joinMember(MemberRequestDTO.JoinDto request) {

    Member newMember = MemberConverter.toMember(request);

    // 인코딩을 하는 이유
    // 평문 비밀번호르 DB에 저장하지 않아 보안 강화, 단방향 해시 함수 사용으로 원본 복원 불가
    newMember.encodePassword(passwordEncoder.encode(request.getPassword()));

    // Member newMember = MemberConverter.toMember(request);
    List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
        .map(category -> {
          return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
        }).collect(Collectors.toList());

    List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

    memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

    return memberRepository.save(newMember);
  }

  public boolean doAllCategoriesExist(List<Long> categoryIds) {
    return categoryIds.stream().
        allMatch(foodCategoryRepository::existsById);
  }

  @Override
  public MemberResponseDTO.LoginResultDTO loginMember(MemberRequestDTO.LoginRequestDTO request) {
    Member member = memberRepository.findByEmail(request.getEmail())
        .orElseThrow(()-> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

    if(!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
      throw new MemberHandler(ErrorStatus.INVALID_PASSWORD);
    }

    Authentication authentication = new UsernamePasswordAuthenticationToken(
        member.getEmail(), null,
        Collections.singleton(() -> member.getRole().name())
    );

    String accessToken = jwtTokenProvider.generateToken(authentication);

    return MemberConverter.toLoginResultDTO(
        member.getId(),
        accessToken
    );
  }
}



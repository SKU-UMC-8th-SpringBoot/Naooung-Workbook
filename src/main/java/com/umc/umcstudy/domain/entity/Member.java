package com.umc.umcstudy.domain.entity;

import com.umc.umcstudy.domain.common.BaseEntity;
import com.umc.umcstudy.domain.enums.Gender;
import com.umc.umcstudy.domain.enums.MemberStatus;
import com.umc.umcstudy.domain.enums.SocialType;
import com.umc.umcstudy.domain.mapping.MemberAgree;
import com.umc.umcstudy.domain.mapping.MemberMission;
import com.umc.umcstudy.domain.mapping.MemberPrefer;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 20)
  private String name;

  private Integer age;

  @Column(nullable = false, length = 40)
  private String address;

  @Column(nullable = false, length = 40)
  private String specAddress;

  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "VARCHAR(10)")
  private Gender gender;

  @Enumerated(EnumType.STRING)
  private SocialType socialType;

  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
  private MemberStatus status;

  private LocalDate inactiveDate;

  @Column(nullable = false, length = 50)
  private String email;

  private Integer point;

  @Builder.Default
  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<MemberAgree> memberAgreeList = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<MemberPrefer> memberPreferList = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<Review> reviewList = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<MemberMission> memberMissionList = new ArrayList<>();
}

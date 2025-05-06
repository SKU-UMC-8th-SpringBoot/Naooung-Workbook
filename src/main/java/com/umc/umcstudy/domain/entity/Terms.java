package com.umc.umcstudy.domain.entity;

import com.umc.umcstudy.domain.common.BaseEntity;
import com.umc.umcstudy.domain.mapping.MemberAgree;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Terms extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private String body;

  private Boolean optional;

  @OneToMany(mappedBy = "terms", cascade = CascadeType.ALL)
  private List<MemberAgree> memberAgreeList = new ArrayList<>();

}
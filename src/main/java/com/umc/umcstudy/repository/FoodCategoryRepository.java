package com.umc.umcstudy.repository;

import com.umc.umcstudy.domain.entity.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {

}

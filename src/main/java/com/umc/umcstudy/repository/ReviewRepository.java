package com.umc.umcstudy.repository;

import com.umc.umcstudy.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}

package com.umc.umcstudy.converter;

import com.umc.umcstudy.domain.entity.Region;
import com.umc.umcstudy.domain.entity.Review;
import com.umc.umcstudy.domain.entity.Store;
import com.umc.umcstudy.web.dto.store.StoreRequestDto;
import com.umc.umcstudy.web.dto.store.StoreResponseDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;

public class StoreConverter {

  public static StoreResponseDto.AddStoreDTO toAddStoreDTO(Store store) {
    return StoreResponseDto.AddStoreDTO.builder()
        .storeId(store.getId())
        .createdAt(LocalDateTime.now())
        .build();
  }

  public static Store toStore(Region region, StoreRequestDto.AddDto request) {

    return Store.builder()
        .name(request.getName())
        .address(request.getAddress())
        .region(region)
        .build();
  }

  public static StoreResponseDto.ReviewPreViewDTO reviewPreViewDTO(Review review){
    return StoreResponseDto.ReviewPreViewDTO.builder()
        .ownerNickname(review.getMember().getName())
        .score(review.getScore())
        .createdAt(review.getCreatedAt().toLocalDate())
        .body(review.getBody())
        .build();
  }
  public static StoreResponseDto.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){

    List<StoreResponseDto.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
        .map(StoreConverter::reviewPreViewDTO).collect(Collectors.toList());

    return StoreResponseDto.ReviewPreViewListDTO.builder()
        .isLast(reviewList.isLast())
        .isFirst(reviewList.isFirst())
        .totalPage(reviewList.getTotalPages())
        .totalElements(reviewList.getTotalElements())
        .listSize(reviewPreViewDTOList.size())
        .reviewList(reviewPreViewDTOList)
        .build();
  }
}

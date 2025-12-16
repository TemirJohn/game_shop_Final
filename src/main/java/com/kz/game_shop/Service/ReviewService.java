package com.kz.game_shop.Service;

import com.kz.game_shop.dto.ReviewDto;
import com.kz.game_shop.entity.Review;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> getReviewsByGame(Long gameId);
    ReviewDto getReviewById(Long id);
    ReviewDto updateReview(Long id, ReviewDto reviewDto);
    ReviewDto addReview(ReviewDto reviewDto);
    void deleteReview(Long id);
}

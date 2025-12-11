package com.kz.game_shop.Service;

import com.kz.game_shop.dto.ReviewDto;
import com.kz.game_shop.entity.Review;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> getReviewsByGame(Long gameId);
    ReviewDto addReview(ReviewDto reviewDto);
}

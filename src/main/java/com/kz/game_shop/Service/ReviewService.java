package com.kz.game_shop.Service;

import com.kz.game_shop.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getReviewsByGame(Long gameId);
    Review addReview(Long userId, Long gameId, Review review);
}

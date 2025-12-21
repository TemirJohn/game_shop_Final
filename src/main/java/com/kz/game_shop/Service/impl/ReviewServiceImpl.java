package com.kz.game_shop.Service.impl;

import com.kz.game_shop.Service.ReviewService;
import com.kz.game_shop.dto.ReviewDto;
import com.kz.game_shop.entity.Game;
import com.kz.game_shop.entity.Review;
import com.kz.game_shop.entity.User;
import com.kz.game_shop.mapper.ReviewMapper;
import com.kz.game_shop.Repository.GameRepository;
import com.kz.game_shop.Repository.ReviewRepo;
import com.kz.game_shop.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepo reviewRepository;
    private final UserRepo userRepository;
    private final GameRepository gameRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public List<ReviewDto> getReviewsByGame(Long gameId) {
        List<Review> allReviews = reviewRepository.findAll();
        List<ReviewDto> gameReviews = new ArrayList<>();

        for (Review review : allReviews) {
            if (review.getGame() != null && review.getGame().getId().equals(gameId)) {
                gameReviews.add(reviewMapper.toDto(review));
            }
        }
        return gameReviews;
    }

    @Override
    public ReviewDto getReviewById(Long id) {
        Review review = reviewRepository.findById(id).orElse(null);
        return reviewMapper.toDto(review);
    }


    @Override
    public ReviewDto addReview(ReviewDto reviewDto) {
        User user = userRepository.findById(reviewDto.getUserId()).orElse(null);
        Game game = gameRepository.findById(reviewDto.getGameId()).orElse(null);

        Review review = reviewMapper.toEntity(reviewDto);
        review.setUser(user);
        review.setGame(game);

        Review savedReview = reviewRepository.save(review);
        return reviewMapper.toDto(savedReview);
    }

    @Override
    public ReviewDto updateReview(Long id, ReviewDto reviewDto) {
        Review existingReview = reviewRepository.findById(id).orElse(null);

        existingReview.setContent(reviewDto.getContent());
        existingReview.setRating(reviewDto.getRating());

        Review savedReview = reviewRepository.save(existingReview);
        return reviewMapper.toDto(savedReview);
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
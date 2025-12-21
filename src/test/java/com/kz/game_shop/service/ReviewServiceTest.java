package com.kz.game_shop.service;

import com.kz.game_shop.Service.ReviewService;
import com.kz.game_shop.dto.ReviewDto;
import com.kz.game_shop.entity.Game;
import com.kz.game_shop.entity.User;
import com.kz.game_shop.Repository.GameRepository;
import com.kz.game_shop.Repository.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepo userRepo;

    @Test
    void getReviewsByGame() {
        User user = new User();
        user.setUsername("reviewer_1_" + System.currentTimeMillis());
        user.setEmail("test1@test.com");
        user.setPassword("password");
        userRepo.save(user);

        Game game = new Game();
        game.setTitle("Game for Reviews");
        game.setDescription("Desc");
        game.setPrice(10.0);
        gameRepository.save(game);

        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setContent("Awesome game!");
        reviewDto.setRating(5);
        reviewDto.setUserId(user.getId());
        reviewDto.setGameId(game.getId());
        reviewService.addReview(reviewDto);

        List<ReviewDto> reviewList = reviewService.getReviewsByGame(game.getId());

        Assertions.assertNotNull(reviewList);
        Assertions.assertFalse(reviewList.isEmpty());

        ReviewDto fetchedReview = reviewList.get(0);
        Assertions.assertEquals(game.getId(), fetchedReview.getGameId());
        Assertions.assertEquals("Awesome game!", fetchedReview.getContent());
    }

    @Test
    void getReviewById() {
        User user = new User();
        user.setUsername("reviewer_2_" + System.currentTimeMillis());
        user.setEmail("test2@test.com");
        user.setPassword("password");
        userRepo.save(user);

        Game game = new Game();
        game.setTitle("Game for ID check");
        game.setDescription("Desc");
        game.setPrice(20.0);
        gameRepository.save(game);

        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setContent("Checking ID");
        reviewDto.setRating(4);
        reviewDto.setUserId(user.getId());
        reviewDto.setGameId(game.getId());

        ReviewDto savedReview = reviewService.addReview(reviewDto);

        ReviewDto foundReview = reviewService.getReviewById(savedReview.getId());

        Assertions.assertNotNull(foundReview);
        Assertions.assertEquals(savedReview.getId(), foundReview.getId());
        Assertions.assertEquals("Checking ID", foundReview.getContent());
    }

    @Test
    void addReview() {
        User user = new User();
        user.setUsername("reviewer_3_" + System.currentTimeMillis());
        user.setEmail("test3@test.com");
        user.setPassword("password");
        userRepo.save(user);

        Game game = new Game();
        game.setTitle("New Game Release");
        game.setDescription("Desc");
        game.setPrice(60.0);
        gameRepository.save(game);

        ReviewDto newReview = new ReviewDto();
        newReview.setContent("Best game ever");
        newReview.setRating(5);
        newReview.setUserId(user.getId());
        newReview.setGameId(game.getId());

        ReviewDto savedReview = reviewService.addReview(newReview);

        Assertions.assertNotNull(savedReview);
        Assertions.assertNotNull(savedReview.getId());
        Assertions.assertEquals("Best game ever", savedReview.getContent());
        Assertions.assertEquals(5, savedReview.getRating());
        Assertions.assertEquals(user.getId(), savedReview.getUserId());
    }

    @Test
    void updateReview() {
        User user = new User();
        user.setUsername("reviewer_4_" + System.currentTimeMillis());
        user.setEmail("test4@test.com");
        user.setPassword("password");
        userRepo.save(user);

        Game game = new Game();
        game.setTitle("Game to Update");
        game.setDescription("Desc");
        game.setPrice(15.0);
        gameRepository.save(game);

        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setContent("Original Content");
        reviewDto.setRating(1);
        reviewDto.setUserId(user.getId());
        reviewDto.setGameId(game.getId());

        ReviewDto saved = reviewService.addReview(reviewDto);

        saved.setContent("Updated Content");
        saved.setRating(3);

        ReviewDto updated = reviewService.updateReview(saved.getId(), saved);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals("Updated Content", updated.getContent());
        Assertions.assertEquals(3, updated.getRating());
        Assertions.assertEquals(saved.getId(), updated.getId());
    }

    @Test
    void deleteReview() {
        User user = new User();
        user.setUsername("reviewer_5_" + System.currentTimeMillis());
        user.setEmail("test5@test.com");
        user.setPassword("password");
        userRepo.save(user);

        Game game = new Game();
        game.setTitle("Game to Delete Review");
        game.setDescription("Desc");
        game.setPrice(5.0);
        gameRepository.save(game);

        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setContent("To be deleted");
        reviewDto.setRating(2);
        reviewDto.setUserId(user.getId());
        reviewDto.setGameId(game.getId());

        ReviewDto saved = reviewService.addReview(reviewDto);
        Long reviewId = saved.getId();

        reviewService.deleteReview(reviewId);

        ReviewDto deletedReview = reviewService.getReviewById(reviewId);
        Assertions.assertNull(deletedReview);
    }
}
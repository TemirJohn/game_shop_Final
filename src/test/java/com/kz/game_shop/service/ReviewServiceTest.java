package com.kz.game_shop.service;

import com.kz.game_shop.Service.GameService;
import com.kz.game_shop.Service.ReviewService;
import com.kz.game_shop.dto.GameDto;
import com.kz.game_shop.dto.ReviewDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private GameService gameService;

    @Test
    void getReviewsByGame() {
        Random random = new Random();
        List<GameDto> allGames = gameService.getAllGames();
        Assertions.assertFalse(allGames.isEmpty());

        int randomIndex = random.nextInt(allGames.size());
        Long gameId = allGames.get(randomIndex).getId();

        List<ReviewDto> reviewDtoList = reviewService.getReviewsByGame(gameId);

        // Список может быть пустым (если отзывов нет), но не null
        Assertions.assertNotNull(reviewDtoList);

        for (ReviewDto reviewDto : reviewDtoList) {
            Assertions.assertNotNull(reviewDto);
            Assertions.assertNotNull(reviewDto.getId());
            Assertions.assertNotNull(reviewDto.getContent());
            Assertions.assertEquals(gameId, reviewDto.getGameId());
        }
    }
}
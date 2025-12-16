package com.kz.game_shop.mapper;

import com.kz.game_shop.dto.ReviewDto;
import com.kz.game_shop.entity.Game;
import com.kz.game_shop.entity.Review;
import com.kz.game_shop.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReviewMapperTest {

    @Autowired
    private ReviewMapper reviewMapper;

    @Test
    void toDtoTest() {
        User user = new User();
        user.setId(77L);
        user.setUsername("ReviewerKing");

        Game game = new Game();
        game.setId(99L);
        game.setTitle("Elden Ring");

        Review review = new Review();
        review.setId(1L);
        review.setContent("Great game!");
        review.setRating(5);
        review.setUser(user);
        review.setGame(game);

        ReviewDto dto = reviewMapper.toDto(review);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(review.getId(), dto.getId());
        Assertions.assertEquals(review.getContent(), dto.getContent());
        Assertions.assertEquals(review.getRating(), dto.getRating());

        // Проверка полей из связей
        Assertions.assertEquals(user.getId(), dto.getUserId());
        Assertions.assertEquals(user.getUsername(), dto.getUsername());
        Assertions.assertEquals(game.getId(), dto.getGameId());
    }

    @Test
    void toEntityTest() {
        ReviewDto dto = new ReviewDto();
        dto.setId(1L);
        dto.setContent("Bad game");
        dto.setRating(2);
        dto.setUserId(77L);
        dto.setGameId(99L);

        Review entity = reviewMapper.toEntity(dto);

        Assertions.assertNotNull(entity);
        Assertions.assertEquals(dto.getId(), entity.getId());
        Assertions.assertEquals(dto.getContent(), entity.getContent());
        Assertions.assertEquals(dto.getRating(), entity.getRating());

        // User и Game игнорируются
        Assertions.assertNull(entity.getUser());
        Assertions.assertNull(entity.getGame());
    }
}

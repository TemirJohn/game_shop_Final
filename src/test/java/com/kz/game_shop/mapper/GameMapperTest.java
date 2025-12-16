package com.kz.game_shop.mapper;

import com.kz.game_shop.dto.GameDto;
import com.kz.game_shop.entity.Category;
import com.kz.game_shop.entity.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GameMapperTest {

    @Autowired
    private GameMapper gameMapper;

    @Test
    void toDtoTest() {
        Category category = new Category();
        category.setId(5L);
        category.setName("Strategy");

        Game game = new Game();
        game.setId(1L);
        game.setTitle("Civilization VI");
        game.setDescription("Turn-based strategy");
        game.setPrice(29.99);
        game.setCategory(category);

        GameDto dto = gameMapper.toDto(game);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(game.getId(), dto.getId());
        Assertions.assertEquals(game.getTitle(), dto.getTitle());
        Assertions.assertEquals(game.getDescription(), dto.getDescription());
        Assertions.assertEquals(game.getPrice(), dto.getPrice());


        Assertions.assertNotNull(dto.getCategory());
        Assertions.assertEquals(category.getId(), dto.getCategory().getId());
        Assertions.assertEquals(category.getName(), dto.getCategory().getName());
    }

    @Test
    void toEntityTest() {
        GameDto dto = new GameDto();
        dto.setId(1L);
        dto.setTitle("Civilization VI");
        dto.setDescription("Strategy");
        dto.setPrice(50.0);

        Game entity = gameMapper.toEntity(dto);

        Assertions.assertNotNull(entity);
        Assertions.assertEquals(dto.getId(), entity.getId());
        Assertions.assertEquals(dto.getTitle(), entity.getTitle());
        Assertions.assertEquals(dto.getDescription(), entity.getDescription());
        Assertions.assertEquals(dto.getPrice(), entity.getPrice());

        Assertions.assertNull(entity.getCategory());
    }
}
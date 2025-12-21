package com.kz.game_shop.mapper;

import com.kz.game_shop.dto.GameDto;
import com.kz.game_shop.entity.Category;
import com.kz.game_shop.entity.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    void toDtoListTest() {
        Category category = new Category();
        category.setId(5L);
        category.setName("Strategy");

        Game game1 = new Game();
        game1.setId(1L);
        game1.setTitle("Civilization VI");
        game1.setDescription("Turn-based strategy");
        game1.setPrice(29.99);
        game1.setCategory(category);

        Game game2 = new Game();
        game2.setId(2L);
        game2.setTitle("XCOM 2");
        game2.setDescription("Tactical strategy");
        game2.setPrice(19.99);
        game2.setCategory(category);

        List<Game> gameList = new ArrayList<>();
        gameList.add(game1);
        gameList.add(game2);

        List<GameDto> gameDtoList = gameMapper.toDtoList(gameList);

        Assertions.assertNotNull(gameDtoList);
        Assertions.assertNotEquals(0, gameDtoList.size());
        Assertions.assertEquals(gameList.size(), gameDtoList.size());

        for (int i = 0; i < gameDtoList.size(); i++) {
            Game gameEntity = gameList.get(i);
            GameDto gameDto = gameDtoList.get(i);

            Assertions.assertNotNull(gameDto);

            Assertions.assertEquals(gameEntity.getId(), gameDto.getId());
            Assertions.assertEquals(gameEntity.getTitle(), gameDto.getTitle());
            Assertions.assertEquals(gameEntity.getDescription(), gameDto.getDescription());
            Assertions.assertEquals(gameEntity.getPrice(), gameDto.getPrice());

            Assertions.assertNotNull(gameDto.getCategory());
            Assertions.assertEquals(gameEntity.getCategory().getId(), gameDto.getCategory().getId());
            Assertions.assertEquals(gameEntity.getCategory().getName(), gameDto.getCategory().getName());
        }
    }
}
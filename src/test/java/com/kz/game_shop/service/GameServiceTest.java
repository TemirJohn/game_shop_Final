package com.kz.game_shop.service;

import com.kz.game_shop.Service.CategoryService;
import com.kz.game_shop.Service.GameService;
import com.kz.game_shop.dto.CategoryDto;
import com.kz.game_shop.dto.GameDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class GameServiceTest {

    @Autowired
    private GameService gameService;

    @Autowired
    private CategoryService categoryService;

    @Test
    void getAllGames() {
        List<GameDto> gameDtoList = gameService.getAllGames();

        Assertions.assertNotNull(gameDtoList);
        Assertions.assertNotEquals(0, gameDtoList.size());

        for (GameDto gameDto : gameDtoList) {
            Assertions.assertNotNull(gameDto);
            Assertions.assertNotNull(gameDto.getId());
            Assertions.assertNotNull(gameDto.getTitle());
            Assertions.assertNotNull(gameDto.getDescription());
        }
    }

    @Test
    void getGameById() {
        Random random = new Random();
        List<GameDto> allGames = gameService.getAllGames();
        Assertions.assertFalse(allGames.isEmpty());

        int randomIndex = random.nextInt(allGames.size());
        Long someIndex = allGames.get(randomIndex).getId();

        GameDto gameDto = gameService.getGameById(someIndex);

        Assertions.assertNotNull(gameDto);
        Assertions.assertNotNull(gameDto.getId());
        Assertions.assertNotNull(gameDto.getTitle());
        Assertions.assertEquals(someIndex, gameDto.getId());
    }

    @Test
    void createGame() {
        List<CategoryDto> categories = categoryService.getAllCategories();
        Assertions.assertFalse(categories.isEmpty());
        Long categoryId = categories.get(0).getId();

        GameDto newGame = new GameDto();
        newGame.setTitle("Test Game " + System.currentTimeMillis());
        newGame.setDescription("Test Description");
        newGame.setPrice(59.99);

        GameDto savedGame = gameService.createGame(newGame);

        Assertions.assertNotNull(savedGame);
        Assertions.assertNotNull(savedGame.getId());
        Assertions.assertEquals(newGame.getTitle(), savedGame.getTitle());
    }

    @Test
    void updateGame() {
        List<CategoryDto> categories = categoryService.getAllCategories();
        if (categories.isEmpty()) return;

        GameDto gameDto = new GameDto();
        gameDto.setTitle("Original Title");
        gameDto.setDescription("Desc");
        gameDto.setPrice(10.0);

        GameDto saved = gameService.createGame(gameDto);

        saved.setTitle("Updated Title");
        saved.setPrice(20.0);

        GameDto updated = gameService.updateGame(saved.getId(), saved);

        Assertions.assertEquals("Updated Title", updated.getTitle());
        Assertions.assertEquals(20.0, updated.getPrice());
    }

//    @Test
//    void deleteGame() {
//        ategoryDto category = getOrCreateCategory();
//
//        // 1. Создаем НОВУЮ игру специально для удаления.
//        // У нее НЕТ отзывов, поэтому Foreign Key ошибка не вылетит.
//        GameDto gameDto = new GameDto();
//        gameDto.setTitle("Game To Delete");
//        gameDto.setCategoryId(category.getId());
//        GameDto saved = gameService.createGame(gameDto, null);
//        Long id = saved.getId();
//
//        // 2. Удаляем
//        gameService.deleteGame(id);
//
//        // 3. Проверяем, что игра больше не находится
//        Assertions.assertThrows(RuntimeException.class, () -> {
//            gameService.getGameById(id);
//        });
//
//    }
}

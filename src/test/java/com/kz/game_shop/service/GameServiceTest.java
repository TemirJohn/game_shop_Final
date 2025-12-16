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
            if (gameDto.getCategoryId() != null) {
                Assertions.assertNotNull(gameDto.getCategoryId());
            }
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
        newGame.setCategoryId(categoryId);

        GameDto savedGame = gameService.createGame(newGame, null);

        Assertions.assertNotNull(savedGame);
        Assertions.assertNotNull(savedGame.getId());
        Assertions.assertEquals(newGame.getTitle(), savedGame.getTitle());
        Assertions.assertEquals(categoryId, savedGame.getCategoryId());
    }


    @Test
    void deleteGame() {
        Random random = new Random();
        List<GameDto> allGames = gameService.getAllGames();
        Assertions.assertFalse(allGames.isEmpty());

        int randomIndex = random.nextInt(allGames.size());
        Long someIndex = allGames.get(randomIndex).getId();

        gameService.deleteGame(someIndex);

        Assertions.assertThrows(RuntimeException.class, () -> {
            gameService.getGameById(someIndex);
        });
    }
}

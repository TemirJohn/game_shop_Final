package com.kz.game_shop.service;

import com.kz.game_shop.Service.CategoryService;
import com.kz.game_shop.Service.GameService;
import com.kz.game_shop.dto.CategoryDto;
import com.kz.game_shop.dto.GameDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@SpringBootTest
@Transactional
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
        Long categoryId;
        if (categories.isEmpty()) {
            CategoryDto newCat = new CategoryDto();
            newCat.setName("Temp Category");
            categoryId = categoryService.createCategory(newCat).getId();
        } else {
            categoryId = categories.get(0).getId();
        }

        GameDto newGame = new GameDto();
        newGame.setTitle("Test Game " + System.currentTimeMillis());
        newGame.setDescription("Test Description");
        newGame.setPrice(59.99);

        CategoryDto catDto = new CategoryDto();
        catDto.setId(categoryId);
        newGame.setCategory(catDto);

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

        CategoryDto catDto = new CategoryDto();
        catDto.setId(categories.get(0).getId());
        gameDto.setCategory(catDto);

        GameDto saved = gameService.createGame(gameDto);

        saved.setTitle("Updated Title");
        saved.setPrice(20.0);

        GameDto updated = gameService.updateGame(saved.getId(), saved);

        Assertions.assertEquals("Updated Title", updated.getTitle());
        Assertions.assertEquals(20.0, updated.getPrice());
    }

    @Test
    void deleteGame() {
        GameDto gameDto = new GameDto();
        gameDto.setTitle("Game To Delete");
        gameDto.setDescription("Desc");
        gameDto.setPrice(10.0);

        GameDto saved = gameService.createGame(gameDto);
        Long idToDelete = saved.getId();

        gameService.deleteGame(idToDelete);
        GameDto deletedGame = gameService.getGameById(idToDelete);
        Assertions.assertNull(deletedGame);
    }
}

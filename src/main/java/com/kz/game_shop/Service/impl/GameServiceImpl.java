package com.kz.game_shop.Service.impl;
import com.kz.game_shop.Repository.CategoryRepository;
import com.kz.game_shop.Repository.GameRepository;
import com.kz.game_shop.Service.CategoryService;
import com.kz.game_shop.Service.GameService;
import com.kz.game_shop.entity.Category;
import com.kz.game_shop.entity.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final CategoryRepository categoryRepository;

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game getGameById(Long id) {
        return gameRepository.findById(id).orElseThrow(() -> new RuntimeException("Game not found"));
    }

    public Game createGame(Game game, Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        game.setCategory(category);
        return gameRepository.save(game);
    }

    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }
}

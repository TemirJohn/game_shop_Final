package com.kz.game_shop.Service;

import com.kz.game_shop.entity.Game;

import java.util.List;

public interface GameService {
    List<Game> getAllGames();
    Game getGameById(Long id);
    Game createGame(Game game, Long categoryId);
    void deleteGame(Long id);
}

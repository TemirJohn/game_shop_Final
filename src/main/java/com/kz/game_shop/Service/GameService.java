package com.kz.game_shop.Service;

import com.kz.game_shop.dto.GameDto;
import com.kz.game_shop.entity.Game;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface GameService {
    List<GameDto> getAllGames();
    GameDto getGameById(Long id);
    GameDto createGame(GameDto game);
    GameDto updateGame(Long id, GameDto gameDto);
    void deleteGame(Long id);
}

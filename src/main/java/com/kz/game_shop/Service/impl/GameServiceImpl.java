package com.kz.game_shop.Service.impl;

import com.kz.game_shop.Service.GameService;
import com.kz.game_shop.dto.GameDto;
import com.kz.game_shop.entity.Category;
import com.kz.game_shop.entity.Game;
import com.kz.game_shop.mapper.GameMapper;
import com.kz.game_shop.Repository.CategoryRepository;
import com.kz.game_shop.Repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final CategoryRepository categoryRepository;
    private final GameMapper gameMapper;

    @Override
    public List<GameDto> getAllGames() {
        List<Game> games = gameRepository.findAll();
        List<GameDto> gameDtos = new ArrayList<>();

        for (Game game : games) {
            gameDtos.add(gameMapper.toDto(game));
        }
        return gameDtos;
    }

    @Override
    public GameDto getGameById(Long id) {
        Game game = gameRepository.findById(id).orElse(null);
        return gameMapper.toDto(game);
    }


    public GameDto createGame(GameDto gameDto) {
        Game game = gameMapper.toEntity(gameDto);

        if (gameDto.getCategoryId() != null) {
            Category category = categoryRepository.findById(gameDto.getCategoryId()).orElse(null);
            game.setCategory(category);
        }

        Game savedGame = gameRepository.save(game);
        return gameMapper.toDto(savedGame);
    }

    @Override
    public GameDto updateGame(Long id, GameDto gameDto) {
        Game existingGame = gameRepository.findById(id).orElse(null);

        existingGame.setTitle(gameDto.getTitle());
        existingGame.setDescription(gameDto.getDescription());
        existingGame.setPrice(gameDto.getPrice());

        if (gameDto.getCategoryId() != null) {
            Category category = categoryRepository.findById(gameDto.getCategoryId()).orElse(null);
            existingGame.setCategory(category);
        }

        Game savedGame = gameRepository.save(existingGame);
        return gameMapper.toDto(savedGame);
    }

    @Override
    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

}
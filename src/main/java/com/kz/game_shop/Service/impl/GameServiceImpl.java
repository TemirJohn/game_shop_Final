package com.kz.game_shop.Service.impl;

import com.kz.game_shop.Service.GameService;
import com.kz.game_shop.dto.GameDto;
import com.kz.game_shop.entity.Category;
import com.kz.game_shop.entity.Game;
import com.kz.game_shop.entity.Review;
import com.kz.game_shop.mapper.GameMapper;
import com.kz.game_shop.Repository.CategoryRepository;
import com.kz.game_shop.Repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final CategoryRepository categoryRepository;
    private final GameMapper gameMapper;

    @Override
    public List<GameDto> getAllGames() {
        return gameMapper.toDtoList(gameRepository.findAll());
    }

    @Override
    public GameDto getGameById(Long id) {
        Game game = gameRepository.findById(id).orElse(null);
        if (game == null) return null;

        GameDto dto = gameMapper.toDto(game);
        dto.setRating(calculateRating(game.getReviews()));
        return dto;
    }


    public GameDto createGame(GameDto gameDto) {
        Game game = gameMapper.toEntity(gameDto);

        if (gameDto.getCategory() != null && gameDto.getCategory().getId() != null) {
            Category category = categoryRepository.findById(gameDto.getCategory().getId()).orElse(null);
            game.setCategory(category);
        }

        Game savedGame = gameRepository.save(game);

        GameDto savedDto = gameMapper.toDto(savedGame);
        savedDto.setRating(0.0);
        return savedDto;
    }

    @Override
    public GameDto updateGame(Long id, GameDto gameDto) {
        Game existingGame = gameRepository.findById(id).orElse(null);
        if (existingGame == null) return null;

        existingGame.setTitle(gameDto.getTitle());
        existingGame.setDescription(gameDto.getDescription());
        existingGame.setPrice(gameDto.getPrice());

        if (gameDto.getCategory() != null && gameDto.getCategory().getId() != null) {
            Category category = categoryRepository.findById(gameDto.getCategory().getId()).orElse(null);
            existingGame.setCategory(category);
        }

        Game savedGame = gameRepository.save(existingGame);

        GameDto savedDto = gameMapper.toDto(savedGame);
        savedDto.setRating(calculateRating(savedGame.getReviews()));
        return savedDto;
    }

    @Override
    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

    public Double calculateRating(List<Review> reviews) {
        if (reviews == null || reviews.isEmpty()) {
            return 0.0;
        }
        double average = reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
        return Math.round(average * 10.0) / 10.0;
    }

}
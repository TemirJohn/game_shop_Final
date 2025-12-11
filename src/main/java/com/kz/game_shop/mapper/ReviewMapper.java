package com.kz.game_shop.mapper;

import com.kz.game_shop.dto.ReviewDto;
import com.kz.game_shop.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "game.id", target = "gameId")
    ReviewDto toDto(Review review);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "game", ignore = true)
    Review toEntity(ReviewDto reviewDto);
}
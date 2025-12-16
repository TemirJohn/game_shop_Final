package com.kz.game_shop.mapper;

import com.kz.game_shop.dto.GameDto;
import com.kz.game_shop.entity.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface GameMapper {
    GameDto toDto(Game game);

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    Game toEntity(GameDto gameDto);
}
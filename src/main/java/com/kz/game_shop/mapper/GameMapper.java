package com.kz.game_shop.mapper;

import com.kz.game_shop.dto.GameDto;
import com.kz.game_shop.entity.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface GameMapper {
    @Mapping(source = "category.id", target = "categoryId")
    GameDto toDto(Game game);

    @Mapping(target = "category", ignore = true)
    Game toEntity(GameDto gameDto);
}
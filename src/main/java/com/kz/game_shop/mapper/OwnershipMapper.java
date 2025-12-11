package com.kz.game_shop.mapper;

import com.kz.game_shop.dto.OwnershipDto;
import com.kz.game_shop.entity.Ownership;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OwnershipMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "game.id", target = "gameId")
    @Mapping(source = "game.title", target = "gameTitle")
    @Mapping(source = "game.imageUrl", target = "gameImageUrl")
    OwnershipDto toDto(Ownership ownership);
}
package com.kz.game_shop.mapper;

import com.kz.game_shop.dto.UserDto;
import com.kz.game_shop.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PermissionMapper.class})
public interface UserMapper {
    UserDto toDto(User user);

    @Mapping(target = "permissions", ignore = true)
    User toEntity(UserDto userDto);
}
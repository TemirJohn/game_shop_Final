package com.kz.game_shop.mapper;

import com.kz.game_shop.dto.PermissionDto;
import com.kz.game_shop.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionDto toDto(Permission permission);
    Permission toEntity(PermissionDto permissionDto);
}
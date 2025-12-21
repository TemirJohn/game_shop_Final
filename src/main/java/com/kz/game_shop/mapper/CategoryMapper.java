package com.kz.game_shop.mapper;

import com.kz.game_shop.dto.CategoryDto;
import com.kz.game_shop.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    @Mapping(target = "games", ignore = true)
    Category toEntity(CategoryDto categoryDto);
}
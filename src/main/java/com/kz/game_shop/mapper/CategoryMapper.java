package com.kz.game_shop.mapper;

import com.kz.game_shop.dto.CategoryDto;
import com.kz.game_shop.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);
    Category toEntity(CategoryDto categoryDto);
}
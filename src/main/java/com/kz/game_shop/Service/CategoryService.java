package com.kz.game_shop.Service;

import com.kz.game_shop.dto.CategoryDto;
import com.kz.game_shop.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategories();
    CategoryDto createCategory(CategoryDto categoryDto);
    void deleteCategory(Long id);
}

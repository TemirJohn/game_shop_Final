package com.kz.game_shop.Service;

import com.kz.game_shop.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category createCategory(Category category);
    void deleteCategory(Long id);
}

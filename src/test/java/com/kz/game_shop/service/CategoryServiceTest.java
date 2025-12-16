package com.kz.game_shop.service;

import com.kz.game_shop.Service.CategoryService;
import com.kz.game_shop.dto.CategoryDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    void getAllCategories() {
        List<CategoryDto> categoryDtoList = categoryService.getAllCategories();

        Assertions.assertNotNull(categoryDtoList);
        Assertions.assertNotEquals(0, categoryDtoList.size());

        for (CategoryDto categoryDto : categoryDtoList) {
            Assertions.assertNotNull(categoryDto);
            Assertions.assertNotNull(categoryDto.getId());
            Assertions.assertNotNull(categoryDto.getName());
        }
    }

    @Test
    void getCategoryById() {
        List<CategoryDto> allCategories = categoryService.getAllCategories();
        Assertions.assertFalse(allCategories.isEmpty());

        CategoryDto randomCategory = allCategories.get(new Random().nextInt(allCategories.size()));

        CategoryDto fetchedCategory = categoryService.getCategoryById(randomCategory.getId());

        Assertions.assertNotNull(fetchedCategory);
        Assertions.assertEquals(randomCategory.getId(), fetchedCategory.getId());
        Assertions.assertEquals(randomCategory.getName(), fetchedCategory.getName());
    }

    @Test
    void updateCategory() {
        CategoryDto dto = new CategoryDto();
        dto.setName("Old Name");
        CategoryDto saved = categoryService.createCategory(dto);

        saved.setName("Updated Name");
        CategoryDto updated = categoryService.updateCategory(saved.getId(), saved);

        Assertions.assertEquals("Updated Name", updated.getName());
        Assertions.assertEquals(saved.getId(), updated.getId());
    }

    @Test
    void deleteCategory() {
        Random random = new Random();
        List<CategoryDto> allCategories = categoryService.getAllCategories();
        Assertions.assertFalse(allCategories.isEmpty());

        int randomIndex = random.nextInt(allCategories.size());
        Long someIndex = allCategories.get(randomIndex).getId();

        categoryService.deleteCategory(someIndex);

        List<CategoryDto> categoriesAfterDelete = categoryService.getAllCategories();
        Assertions.assertNull(categoriesAfterDelete);
    }
}

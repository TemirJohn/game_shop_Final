package com.kz.game_shop.mapper;

import com.kz.game_shop.dto.CategoryDto;
import com.kz.game_shop.entity.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryMapperTest {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    void toDtoTest() {
        Category category = new Category();
        category.setId(1L);
        category.setName("RPG");

        CategoryDto dto = categoryMapper.toDto(category);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(category.getId(), dto.getId());
        Assertions.assertEquals(category.getName(), dto.getName());
    }

    @Test
    void toEntityTest() {
        CategoryDto dto = new CategoryDto();
        dto.setId(1L);
        dto.setName("Shooter");

        Category entity = categoryMapper.toEntity(dto);

        Assertions.assertNotNull(entity);
        Assertions.assertEquals(dto.getId(), entity.getId());
        Assertions.assertEquals(dto.getName(), entity.getName());
    }
}
package com.kz.game_shop.dto;

import lombok.Data;

@Data
public class GameDto {
    private Long id;
    private String title;
    private String description;
    private Double price;

    private Long categoryId;

    private CategoryDto category;
}

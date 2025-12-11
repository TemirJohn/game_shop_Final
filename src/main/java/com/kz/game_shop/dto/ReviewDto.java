package com.kz.game_shop.dto;

import lombok.Data;

@Data
public class ReviewDto {
    private Long id;
    private String content;
    private Integer rating;

    private Long userId;
    private String username;

    private Long gameId;
}

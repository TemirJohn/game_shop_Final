package com.kz.game_shop.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OwnershipDto {
    private Long id;
    private Long userId;
    private Long gameId;
    private String gameTitle;
    private String gameImageUrl;
    private LocalDateTime purchaseDate;
}

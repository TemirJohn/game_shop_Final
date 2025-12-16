package com.kz.game_shop.dto;

import com.kz.game_shop.entity.Permission;
import lombok.Data;
import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private List<Permission> permissions;
}
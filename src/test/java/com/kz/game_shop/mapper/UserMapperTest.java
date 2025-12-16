package com.kz.game_shop.mapper;

import com.kz.game_shop.dto.UserDto;
import com.kz.game_shop.entity.Permission;
import com.kz.game_shop.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void toDtoTest() {
        Permission permission = new Permission();
        permission.setId(1L);
        permission.setName("ROLE_USER");

        User user = new User();
        user.setId(100L);
        user.setUsername("gamer123");
        user.setEmail("gamer@test.com");
        user.setPassword("secretPass");
        user.setPermissions(List.of(permission));

        UserDto dto = userMapper.toDto(user);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(user.getId(), dto.getId());
        Assertions.assertEquals(user.getUsername(), dto.getUsername());
        Assertions.assertEquals(user.getEmail(), dto.getEmail());
        Assertions.assertEquals(user.getPassword(), dto.getPassword());

        Assertions.assertNotNull(dto.getPermissions());
        Assertions.assertEquals(1, dto.getPermissions().size());
        Assertions.assertEquals(permission.getId(), dto.getPermissions().get(0).getId());
        Assertions.assertEquals(permission.getName(), dto.getPermissions().get(0).getName());
    }

    @Test
    void toEntityTest() {
        UserDto dto = new UserDto();
        dto.setId(100L);
        dto.setUsername("gamer123");
        dto.setEmail("gamer@test.com");
        dto.setPassword("12345");

        User entity = userMapper.toEntity(dto);

        Assertions.assertNotNull(entity);
        Assertions.assertEquals(dto.getId(), entity.getId());
        Assertions.assertEquals(dto.getUsername(), entity.getUsername());
        Assertions.assertEquals(dto.getEmail(), entity.getEmail());
        Assertions.assertEquals(dto.getPassword(), entity.getPassword());

        Assertions.assertNull(entity.getPermissions());
    }
}
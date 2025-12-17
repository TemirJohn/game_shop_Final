package com.kz.game_shop.service;

import com.kz.game_shop.Repository.GameRepository;
import com.kz.game_shop.Repository.PermissionRepo;
import com.kz.game_shop.Repository.UserRepo;
import com.kz.game_shop.Service.impl.UserServiceImpl;
import com.kz.game_shop.dto.GameDto;
import com.kz.game_shop.entity.Game;
import com.kz.game_shop.entity.Permission;
import com.kz.game_shop.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PermissionRepo permissionRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void loadUserByUsernameTest() {
        User user = new User();
        String username = "test_auth_user_" + System.currentTimeMillis();
        user.setUsername(username);
        user.setPassword("password123");
        user.setEmail("auth@test.com");
        userRepo.save(user);

        UserDetails userDetails = userService.loadUserByUsername(username);

        Assertions.assertNotNull(userDetails);
        Assertions.assertEquals(username, userDetails.getUsername());
    }


    @Test
    void registerUserTest() {
        if (permissionRepo.findByName("ROLE_USER") == null) {
            Permission permission = new Permission();
            permission.setName("ROLE_USER");
            permissionRepo.save(permission);
        }

        User user = new User();
        String username = "new_user_" + System.currentTimeMillis();
        user.setUsername(username);
        user.setPassword("plainPassword");
        user.setEmail("new@test.com");

        userService.registr(user);

        User savedUser = userRepo.findByUsername(username).orElse(null);
        Assertions.assertNotNull(savedUser);

        Assertions.assertNotEquals("plainPassword", savedUser.getPassword());
        Assertions.assertTrue(passwordEncoder.matches("plainPassword", savedUser.getPassword()));

        Assertions.assertNotNull(savedUser.getPermissions());
        Assertions.assertFalse(savedUser.getPermissions().isEmpty());
        Assertions.assertEquals("ROLE_USER", savedUser.getPermissions().get(0).getName());
    }

    @Test
    void buyGameTest() {
        User user = new User();
        user.setUsername("buyer_" + System.currentTimeMillis());
        user.setPassword("pass");
        user.setEmail("buyer@test.com");
        userRepo.save(user);

        Game game = new Game();
        game.setTitle("Elden Ring");
        game.setDescription("RPG");
        game.setPrice(59.99);
        gameRepository.save(game);

        userService.buyGame(user.getId(), game.getId());

        User updatedUser = userRepo.findById(user.getId()).orElseThrow();

        Assertions.assertNotNull(updatedUser.getGames());
        Assertions.assertFalse(updatedUser.getGames().isEmpty());
        Assertions.assertEquals(1, updatedUser.getGames().size());
        Assertions.assertEquals(game.getId(), updatedUser.getGames().get(0).getId());
    }

    @Test
    void getUserGamesTest() {
        User user = new User();
        user.setUsername("gamer_pro_" + System.currentTimeMillis());
        user.setPassword("pass");
        user.setEmail("pro@test.com");
        userRepo.save(user);

        Game game1 = new Game();
        game1.setTitle("Game One");
        game1.setPrice(10.0);
        gameRepository.save(game1);

        Game game2 = new Game();
        game2.setTitle("Game Two");
        game2.setPrice(20.0);
        gameRepository.save(game2);


        userService.buyGame(user.getId(), game1.getId());
        userService.buyGame(user.getId(), game2.getId());

        List<GameDto> userGames = userService.getUserGames(user.getId());

        Assertions.assertNotNull(userGames);
        Assertions.assertEquals(2, userGames.size());

        boolean hasGame1 = userGames.stream().anyMatch(dto -> dto.getTitle().equals("Game One"));
        boolean hasGame2 = userGames.stream().anyMatch(dto -> dto.getTitle().equals("Game Two"));

        Assertions.assertTrue(hasGame1);
        Assertions.assertTrue(hasGame2);
    }
}
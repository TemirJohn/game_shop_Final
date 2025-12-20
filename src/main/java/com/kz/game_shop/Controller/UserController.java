package com.kz.game_shop.Controller;

import com.kz.game_shop.Service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/{userId}/games/{gameId}")
    public ResponseEntity<?> buyGame(@PathVariable Long userId, @PathVariable Long gameId) {
        userService.buyGame(userId, gameId);
        return ResponseEntity.ok("Game added to library");
    }

    @GetMapping("/{userId}/games")
    public ResponseEntity<?> getUserGames(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserGames(userId));
    }

    @DeleteMapping("/{userId}/games/{gameId}")
    public ResponseEntity<?> returnGame(@PathVariable Long userId, @PathVariable Long gameId) {
        try {
            userService.removeGame(userId, gameId);
            return ResponseEntity.ok("Game returned successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

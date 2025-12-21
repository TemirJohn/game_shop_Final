package com.kz.game_shop.Controller;

import com.kz.game_shop.Service.impl.UserServiceImpl;
import com.kz.game_shop.dto.UserDto;
import com.kz.game_shop.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserServiceImpl userService;

    @GetMapping
    public String getAllTest() {
        return "test";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        userService.registr(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String username = authentication.getName();
        UserDto userDto = userService.getUserByUsername(username);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

}

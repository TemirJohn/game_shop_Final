package com.kz.game_shop.Service.impl;

import com.kz.game_shop.Repository.GameRepository;
import com.kz.game_shop.Repository.PermissionRepo;
import com.kz.game_shop.Repository.UserRepo;
import com.kz.game_shop.dto.GameDto;
import com.kz.game_shop.dto.UserDto;
import com.kz.game_shop.entity.Game;
import com.kz.game_shop.entity.Permission;
import com.kz.game_shop.entity.User;
import com.kz.game_shop.mapper.GameMapper;
import com.kz.game_shop.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired; // Важно: используем Autowired
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PermissionRepo permissionRepository;

    @Autowired
    private GameMapper gameMapper;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElse(null);

        if (Objects.nonNull(user)) {
            return user;
        }
        throw new UsernameNotFoundException("User Not Found");
    }

    public void registr(User user) {
        User check = userRepository.findByUsername(user.getUsername()).orElse(null);
        if (check == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            Permission roleUser = permissionRepository.findByName("ROLE_USER");
            if (roleUser != null) {
                user.setPermissions(List.of(roleUser));
            }
            userRepository.save(user);
        }
    }

    @Transactional
    public void buyGame(Long userId, Long gameId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game not found"));

        if (user.getGames() == null) {
            user.setGames(new ArrayList<>());
        }

        if (!user.getGames().contains(game)) {
            user.getGames().add(game);
            userRepository.save(user);
        }
    }

    @Transactional(readOnly = true)
    public List<GameDto> getUserGames(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getGames() == null) {
            return new ArrayList<>();
        }

        return user.getGames().stream()
                .map(gameMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void removeGame(Long userId, Long gameId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Game gameToRemove = null;

        // Ищем игру в списке пользователя
        if (user.getGames() != null) {
            for (Game game : user.getGames()) {
                if (game.getId().equals(gameId)) {
                    gameToRemove = game;
                    break;
                }
            }
        }

        if (gameToRemove != null) {
            user.getGames().remove(gameToRemove);
            userRepository.save(user);
        } else {
            throw new RuntimeException("Game not found in user library");
        }
    }

    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDto(user);
    }
}
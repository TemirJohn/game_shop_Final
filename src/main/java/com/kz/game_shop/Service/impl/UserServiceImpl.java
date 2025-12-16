package com.kz.game_shop.Service.impl;

import com.kz.game_shop.dto.UserDto;
import com.kz.game_shop.entity.Permission;
import com.kz.game_shop.entity.User;
import com.kz.game_shop.mapper.UserMapper;
import com.kz.game_shop.Repository.PermissionRepo;
import com.kz.game_shop.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final UserRepo userRepository;
    private final PermissionRepo permissionRepository;
    private final UserMapper userMapper;

    @Lazy
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElse(null);

        if (Objects.nonNull(user)) {
            return user;
        }

        throw new UsernameNotFoundException("User Not Found");
    }

    public UserDto registerUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);

        User check = userRepository.findByUsername(user.getUsername()).orElse(null);

        if (check == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            Permission roleUser = permissionRepository.findByName("ROLE_USER");

            if (roleUser == null) {
                roleUser = new Permission();
                roleUser.setName("ROLE_USER");
                roleUser = permissionRepository.save(roleUser);
            }

            user.setPermissions(List.of(roleUser));

            User savedUser = userRepository.save(user);
            return userMapper.toDto(savedUser);
        } else {
            throw new RuntimeException("User already exists");
        }
    }

    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (Objects.nonNull(user)) {
            return userMapper.toDto(user);
        }
        return null;
    }


}
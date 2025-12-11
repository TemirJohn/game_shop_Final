package com.kz.game_shop.Service.impl;

import com.kz.game_shop.Repository.PermissionRepo;
import com.kz.game_shop.Repository.UserRepo;
import com.kz.game_shop.entity.Permission;
import com.kz.game_shop.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final UserRepo userRepository;
    private final PermissionRepo permissionRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public User registerUser(User user) {
        // Хешируем пароль
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Назначаем права по умолчанию
        Permission userRole = permissionRepository.findByName("ROLE_USER");
        if (userRole == null) {
            userRole = new Permission();
            userRole.setName("ROLE_USER");
            permissionRepository.save(userRole);
        }
        user.setPermissions(Collections.singletonList(userRole));

        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}

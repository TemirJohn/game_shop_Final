package com.kz.game_shop.Service.impl;

import com.kz.game_shop.Repository.PermissionRepo;
import com.kz.game_shop.Repository.UserRepo;
import com.kz.game_shop.entity.Permission;
import com.kz.game_shop.entity.User;
import org.springframework.beans.factory.annotation.Autowired; // Важно: используем Autowired
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PermissionRepo permissionRepository;

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
}
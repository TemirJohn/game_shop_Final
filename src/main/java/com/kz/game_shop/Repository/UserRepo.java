package com.kz.game_shop.Repository;

import com.kz.game_shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Long, User> {
}

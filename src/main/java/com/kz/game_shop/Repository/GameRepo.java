package com.kz.game_shop.Repository;

import com.kz.game_shop.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepo extends JpaRepository<Long, Game> {
}

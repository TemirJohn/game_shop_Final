package com.kz.game_shop.Repository;

import com.kz.game_shop.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Long, ReviewRepo> {
    List<Review> findByGameId(Long gameId);
}

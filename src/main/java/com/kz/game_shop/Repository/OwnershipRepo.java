package com.kz.game_shop.Repository;

import com.kz.game_shop.entity.Ownership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnershipRepo extends JpaRepository<Long, Ownership> {
    boolean existsByUserIdAndGameId(Long userId, Long gameId);
    List<Ownership> findByUserId(Long userId);
}

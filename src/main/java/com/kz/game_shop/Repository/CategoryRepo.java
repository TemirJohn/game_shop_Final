package com.kz.game_shop.Repository;

import com.kz.game_shop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Long, Category> {
}

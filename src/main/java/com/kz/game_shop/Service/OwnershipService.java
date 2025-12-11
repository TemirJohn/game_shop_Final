package com.kz.game_shop.Service;

import com.kz.game_shop.entity.Ownership;

import java.util.List;

public interface OwnershipService {
    void buyGame(Long userId, Long gameId);
    List<Ownership> getUserLibrary(Long userId);
}

package com.game_diamond.service;

import com.game_diamond.entities.UserCoin;

import java.util.Optional;

public interface UserCoinService {
    Optional<UserCoin> findByUserId(Long userId);
    UserCoin getInfo(Long userId);

    UserCoin createOrUpdate(Long userId, Long amountCoin);
}

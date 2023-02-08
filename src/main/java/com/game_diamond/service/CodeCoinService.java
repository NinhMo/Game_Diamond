package com.game_diamond.service;

import com.game_diamond.entities.CodeCoin;
import com.game_diamond.entities.UserCoin;
import com.game_diamond.payload.response.ActionResult;

public interface CodeCoinService {
    ActionResult topupCoin(String coinCode, Long userId);
}

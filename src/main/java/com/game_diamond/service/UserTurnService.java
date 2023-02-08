package com.game_diamond.service;

import com.game_diamond.entities.UserTurn;
import com.game_diamond.payload.response.BuyTurnRes;

public interface UserTurnService {

    UserTurn createOrUpdate(Long userId, Long amountTurn);
    BuyTurnRes buyTurnByCoin(Long userId, Long amountTurn);
    UserTurn findByUserId(Long userId);
    UserTurn save(UserTurn userTurn);

}

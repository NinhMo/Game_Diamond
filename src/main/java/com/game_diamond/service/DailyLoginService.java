package com.game_diamond.service;

import com.game_diamond.entities.UserTurn;
import com.game_diamond.payload.response.DailyLoginRes;

public interface DailyLoginService {

   Boolean isDailyLogin(Long userId);

   DailyLoginRes addTurnIfDailyLogin(Long userId);

}

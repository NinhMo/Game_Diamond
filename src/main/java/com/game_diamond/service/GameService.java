package com.game_diamond.service;

import com.game_diamond.constant.enum_type.LevelType;
import com.game_diamond.payload.response.ActionResult;

public interface GameService {
    ActionResult create(Long userId, LevelType level);

    ActionResult play(String sessionId, Long userId, int x, int y);

    ActionResult end(String sessionId, Long userId);
}

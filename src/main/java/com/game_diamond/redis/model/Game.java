package com.game_diamond.redis.model;

import com.game_diamond.constant.enum_type.LevelType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Game implements Serializable {
    private Long userId;
    private String sessionId;
    private Long numDiamond = 0L;
    private LevelType level = LevelType.EASY;
    private int[][] map;
}

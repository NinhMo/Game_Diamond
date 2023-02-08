package com.game_diamond.payload.request;

import com.game_diamond.constant.enum_type.LevelType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateGameReq {
    @NotNull
    private Long userId;
    private LevelType level;
}

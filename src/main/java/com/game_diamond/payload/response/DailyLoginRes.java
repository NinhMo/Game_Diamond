package com.game_diamond.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DailyLoginRes {
    private boolean isDailyLogin;
    private Long turn;
}

package com.game_diamond.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BuyTurnRes {
    private Long turn;
    private Long coin;
}

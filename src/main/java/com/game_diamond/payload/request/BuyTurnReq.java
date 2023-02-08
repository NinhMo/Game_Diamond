package com.game_diamond.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BuyTurnReq {

    @NotNull
    private Long userId;

    @NotNull
    private  Long amountTurn;


}

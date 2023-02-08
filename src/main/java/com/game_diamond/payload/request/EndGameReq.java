package com.game_diamond.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EndGameReq extends CommonReq{
    @NotBlank
    private String sessionId;
}

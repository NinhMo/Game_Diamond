package com.game_diamond.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PlayGameReq extends CommonReq{
    @NotNull
    private Integer x;

    @NotNull
    private Integer y;

    @NotBlank
    private String sessionId;
}

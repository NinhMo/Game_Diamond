package com.game_diamond.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Basic {
    private boolean success;
    private Object result;
    private String code;
    private String message;
}

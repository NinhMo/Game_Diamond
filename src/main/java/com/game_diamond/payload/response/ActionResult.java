package com.game_diamond.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActionResult {
    private boolean isSuccess;
    private Object result;
    private String message;
}

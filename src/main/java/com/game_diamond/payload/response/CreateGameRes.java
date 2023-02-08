package com.game_diamond.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateGameRes {
    private Long userId;
    private String sessionId;
    private Long totalTurn;
}

package com.game_diamond.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class GetHistoryTopUpCoin {
    private Long userId;
    private String codeCoin;
    private Boolean status;
    private Long value;
    private Timestamp createdAt;
    private Timestamp updateAt;
}

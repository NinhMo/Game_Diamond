package com.game_diamond.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class TopupRequest extends CommonReq {
    @NotEmpty
    private String code;

}

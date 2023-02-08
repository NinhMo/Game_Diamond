package com.game_diamond.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class LoginReq {
    @NotBlank
    @NotEmpty
    private String userName;

    @NotEmpty
    @NotBlank
    private String password;
}

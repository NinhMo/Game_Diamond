package com.game_diamond.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class SignUpReq {

    @NotBlank
    @NotEmpty
    private String username;
    @NotEmpty
    @NotBlank
    @Email
    private String email;
    @NotEmpty
    @NotBlank
    private String password;
}

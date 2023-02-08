package com.game_diamond.service;

import com.game_diamond.payload.request.LoginReq;
import com.game_diamond.payload.request.SignUpReq;
import com.game_diamond.entities.User;
import com.game_diamond.payload.response.LoginRes;

public interface UserService {

    User getByUseNameOrEmail(String userName, String email);

    User register(SignUpReq singup);

    LoginRes login(LoginReq login);

    User findByUserId(Long userId);


}

package com.game_diamond.controller;


import com.game_diamond.entities.User;
import com.game_diamond.entities.UserTurn;
import com.game_diamond.payload.request.LoginReq;
import com.game_diamond.payload.request.SignUpReq;
import com.game_diamond.payload.response.DailyLoginRes;
import com.game_diamond.payload.response.LoginRes;
import com.game_diamond.payload.response.ResponseFactory;
import com.game_diamond.service.DailyLoginService;
import com.game_diamond.service.UserService;
import com.game_diamond.service.imp.DailyLoginImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class SingUpContr {
    @Autowired
    private UserService userService;

    @Autowired
    private DailyLoginService dailyLoginService;

    @Autowired
    private ResponseFactory responseFactory;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody SignUpReq signUpReq){
        User user = userService.register(signUpReq);
        if(user == null){
            return responseFactory.buildError(HttpStatus.BAD_REQUEST, "ERROR", "Can not create user");
        }

        return responseFactory.buildSuccess(HttpStatus.OK, user, "SUCCESS", "Successfully");

    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginReq loginReq){
            LoginRes user = userService.login(loginReq);
            if (user==null){
                return responseFactory.buildError(HttpStatus.BAD_REQUEST, "ERROR", "Password is error");
        }
            return responseFactory.buildSuccess(HttpStatus.OK, user, "SUCCESS", "Successfully");
    }

    @GetMapping("/check-daily-login")
    public ResponseEntity<?> checkDailyLoginAndAddTurn(@RequestParam Long id){
        DailyLoginRes dailyLoginRes = dailyLoginService.addTurnIfDailyLogin(id);
        return responseFactory.buildSuccess(HttpStatus.OK, dailyLoginRes, "SUCCESS", "Successfully");
    }


}


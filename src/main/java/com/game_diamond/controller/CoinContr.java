package com.game_diamond.controller;

import com.game_diamond.payload.request.CommonReq;
import com.game_diamond.payload.response.ResponseFactory;
import com.game_diamond.service.UserCoinHisService;
import com.game_diamond.service.UserCoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/coin")
public class CoinContr {

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private UserCoinService userCoinService;

    @Autowired
    private UserCoinHisService userCoinHisService;

    @GetMapping("/info")
    public ResponseEntity<?> getCoin(@RequestParam Long userId){
        return responseFactory.buildSuccess(HttpStatus.OK, userCoinService.getInfo(userId), "SUCCESS", "Get info successfully");
    }

    @GetMapping("/history")
    public ResponseEntity<?> getHistory(@RequestParam Long userId,
                                        @RequestParam(required = false, defaultValue = "0") int page,
                                        @RequestParam(required = false, defaultValue = "10") int size){
        return responseFactory.buildSuccess(HttpStatus.OK, userCoinHisService.getUserCoinHisByUserId(userId, page, size), "SUCCESS", "Get info successfully");
    }
}

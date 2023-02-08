package com.game_diamond.controller;

import com.game_diamond.payload.request.TopupRequest;
import com.game_diamond.payload.response.ActionResult;
import com.game_diamond.payload.response.ResponseFactory;
import com.game_diamond.service.CodeCoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/code-coin")
public class TopupCoinContr {
    @Autowired
    private CodeCoinService codeCoinService;

    @Autowired
    private ResponseFactory responseFactory;

    @PostMapping(value = "topup-coin")
    public ResponseEntity<?> topupCoin(@Valid @RequestBody TopupRequest req){
        ActionResult actionResult = codeCoinService.topupCoin(req.getCode(), req.getUserId());
        if (actionResult.isSuccess()){
            return responseFactory.buildSuccess(HttpStatus.OK,actionResult.getResult(),"SUCCESS", "Successfully");
        }
        return responseFactory.buildError(HttpStatus.BAD_REQUEST, "ERROR", "Can't not top up");
    }
}

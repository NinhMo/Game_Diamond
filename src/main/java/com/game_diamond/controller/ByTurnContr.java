package com.game_diamond.controller;

import com.game_diamond.entities.UserTurn;
import com.game_diamond.payload.request.BuyTurnReq;
import com.game_diamond.payload.response.BuyTurnRes;
import com.game_diamond.payload.response.ResponseFactory;
import com.game_diamond.service.UserTurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping(value = "/api/")
public class ByTurnContr {

    @Autowired
    private UserTurnService userTurnService;

    @Autowired
    private ResponseFactory responseFactory;

    @PostMapping(value = "buy-turn")
    public ResponseEntity<?> buyTurnByCoin(@Valid @RequestBody BuyTurnReq buyTurnReq, Principal principal){
        BuyTurnRes buyTurnRes = userTurnService.buyTurnByCoin(buyTurnReq.getUserId(),buyTurnReq.getAmountTurn());
        if (buyTurnRes==null){
            return responseFactory.buildError(HttpStatus.BAD_REQUEST,"ERROR","Can not create UserTurn");
        }
        return responseFactory.buildSuccess(HttpStatus.OK,buyTurnRes,"SUCCESS", "Successfully");
    }
}

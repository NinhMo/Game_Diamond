package com.game_diamond.controller;

import com.game_diamond.constant.enum_type.LevelType;
import com.game_diamond.entities.Session;
import com.game_diamond.payload.request.CreateGameReq;
import com.game_diamond.payload.request.EndGameReq;
import com.game_diamond.payload.request.PlayGameReq;
import com.game_diamond.payload.response.ActionResult;
import com.game_diamond.payload.response.CreateGameRes;
import com.game_diamond.payload.response.ResponseFactory;
import com.game_diamond.service.GameService;
import com.game_diamond.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/game-service")
public class GameContr {

    @Autowired
    private GameService gameService;

    @Autowired
    private SessionService sessionService;
    @Autowired
    private ResponseFactory responseFactory;

    @PostMapping(value = "create-game")
    public  ResponseEntity<?> createGame(@Valid @RequestBody CreateGameReq req){
        ActionResult actionResult = gameService.create(req.getUserId(), req.getLevel());
        if (actionResult==null || !actionResult.isSuccess()){
            return responseFactory.buildError(HttpStatus.BAD_REQUEST, "ERROR", "Fail");
        }
        return responseFactory.buildSuccess(HttpStatus.OK, actionResult.getResult(), "SUCCESS", "Successfully");
    }

    @PostMapping(value = "play-game")
    public  ResponseEntity<?> playGame(@Valid @RequestBody PlayGameReq req){
        ActionResult actionResult = gameService.play(req.getSessionId(), req.getUserId(), req.getX(), req.getY());
        if (actionResult==null || !actionResult.isSuccess()){
            return responseFactory.buildError(HttpStatus.BAD_REQUEST, "ERROR", actionResult == null ? "Fail" : actionResult.getMessage());
        }
        return responseFactory.buildSuccess(HttpStatus.OK, actionResult.getResult(), "SUCCESS", "Successfully");
    }

    @PostMapping(value = "end-game")
    public  ResponseEntity<?> endGame(@Valid @RequestBody EndGameReq req){
        ActionResult actionResult = gameService.end(req.getSessionId(), req.getUserId());
        if (actionResult==null || !actionResult.isSuccess()){
            return responseFactory.buildError(HttpStatus.BAD_REQUEST, "ERROR", actionResult == null ? "Fail" : actionResult.getMessage());
        }
        return responseFactory.buildSuccess(HttpStatus.OK, actionResult.getResult(), "SUCCESS", "Successfully");
    }

}

package com.game_diamond.controller;

import com.game_diamond.payload.response.ResponseFactory;
import com.game_diamond.service.TopGameService;
import com.game_diamond.utils.TimeUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/top-game")
@AllArgsConstructor
public class TopGameContr {

    private ResponseFactory responseFactory;

    private TopGameService topGameService;


    @GetMapping("/by-this-week")
    public ResponseEntity<?> getTopByThisWeek(@RequestParam(required = false, defaultValue = "0") int page,
                                              @RequestParam(required = false, defaultValue = "10") int size ){
        return responseFactory.buildSuccess(HttpStatus.OK, topGameService.topGameByWeek(TimeUtils.timeWeekCode(), page, size ), "SUCCESS", "Get info successfully");
    }

    @GetMapping("/by-this-month")
    public ResponseEntity<?> getTopByThisMonth(@RequestParam(required = false, defaultValue = "0") int page,
                                              @RequestParam(required = false, defaultValue = "10") int size ){
        return responseFactory.buildSuccess(HttpStatus.OK, topGameService.topGameByMonth(TimeUtils.timeMonthCode(), page, size ), "SUCCESS", "Get info successfully");
    }

}

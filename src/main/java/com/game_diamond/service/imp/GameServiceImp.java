package com.game_diamond.service.imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.game_diamond.constant.Constant;
import com.game_diamond.constant.enum_type.LevelType;
import com.game_diamond.entities.*;
import com.game_diamond.payload.response.ActionResult;
import com.game_diamond.payload.response.CreateGameRes;
import com.game_diamond.redis.RedisService;
import com.game_diamond.redis.model.Game;
import com.game_diamond.repository.GameDetailHistoryRepo;
import com.game_diamond.service.*;
import com.game_diamond.utils.TimeUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
@Slf4j
public class GameServiceImp implements GameService {
    private UserService userService;
    private UserTurnService userTurnService;
    private SessionService sessionService;

    private RedisService redisService;
    private UserCoinService userCoinService;
    private GameDetailHistoryRepo gameDetailHistoryRepo;
    private TopGameService topGameService;

    @Override
    public ActionResult create(Long userId, LevelType level) {

        User user = userService.findByUserId(userId);
        if (user == null) {
            return new ActionResult(false, null, "Not exists account");
        }
        UserTurn userTurn = userTurnService.findByUserId(userId);
        if (userTurn == null || userTurn.getTotalTurn() <= 0) {
            return new ActionResult(false, null, "Not enough turns");
        }

        int dimen = 0;
        int diamond = 0;
        if (level == LevelType.EASY) {
            dimen = 5;
            diamond = 5;
        } else if (level == LevelType.MEDIUM) {
            dimen = 7;
            diamond = 6;
        } else {
            dimen = 9;
            diamond = 8;
        }
        Session session = sessionService.create(userId);

//        todo: Thuc hien sinh mang arr[dimen][dimen] voi so diamond chua trong arr do. Quy dinh: -1 ==> Diamond;
//        Luu vao redis
        int[][] map = generateMap(dimen, diamond);

        Game game = new Game();
        game.setUserId(userId);
        game.setSessionId(session.getId());
        game.setMap(map);
        game.setLevel(level);

        try {
            log.info("Map: {}", new ObjectMapper().writeValueAsString(map));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        redisService.save(session.getId(), game, 30L, TimeUnit.MINUTES);

        userTurn.setTotalTurn(userTurn.getTotalTurn() - 1);
        userTurnService.save(userTurn);
        return new ActionResult(true, new CreateGameRes(userId, session.getId(), userTurn.getTotalTurn()), "Create game success");
    }

    @Override
    public ActionResult play(String sessionId, Long userId, int x, int y) {
        User user = userService.findByUserId(userId);
        if (user == null) {
            return new ActionResult(false, null, "Not exists account");
        }
        Game game = (Game) redisService.findByKey(sessionId);
        if (game == null) {
            return new ActionResult(false, null, "Not exists session game");
        }

        int[][] map = game.getMap();
        if (map[x][y] == -99) {
            return new ActionResult(false, null, "You already clicked this one!");
        }

        if (map[x][y] == -1) {
            game.setNumDiamond(game.getNumDiamond() + 1);
            game.getMap()[x][y] = -99;
            redisService.save(sessionId, game, 30L, TimeUnit.MINUTES);
        }

        return new ActionResult(true, game.getMap()[x][y], "Success");
    }

    @Override
    public ActionResult end(String sessionId, Long userId) {
        User user = userService.findByUserId(userId);
        if (user == null) {
            return new ActionResult(false, null, "Not exists account");
        }
        Game game = (Game) redisService.findByKey(sessionId);
        if (game == null) {
            return new ActionResult(false, null, "Not exists session game");
        }
        Long numDiamond = game.getNumDiamond();
        Long totalCoin = 0L;
        if (numDiamond > 0) {
            totalCoin = numDiamond * 10;
            userCoinService.createOrUpdate(userId, totalCoin);
        }

        saveGameHistory(userId, game);
        saveTopGameWeek(userId, game);
        saveTopGameMonth(userId, game);
        closeSession(sessionId);

        redisService.remove(sessionId);
        return new ActionResult(true, game, "End game successfully");

    }

    private void closeSession(String sessionId) {
        sessionService.close(sessionId);
    }

    private void saveTopGameMonth(Long userId, Game game) {
        topGameService.createOrUpdateTopMonthByMonthCode(userId, game.getNumDiamond(), TimeUtils.timeMonthCode());

    }

    private void saveTopGameWeek(Long userId, Game game) {
        topGameService.createOrUpdateTopWeekByWeekCode(userId, game.getNumDiamond(), TimeUtils.timeWeekCode());
    }

    private void saveGameHistory(Long userId, Game game) {
        GameDetailHistory his = new GameDetailHistory();
        his.setUserId(userId);
        his.setDiamond(game.getNumDiamond());
        his.setGameLevelCode(game.getLevel().name());
        his.setSessionId(game.getSessionId());
        gameDetailHistoryRepo.save(his);
    }

    private int[][] generateMap(Integer dimen, Integer diamond) {
        int rDimen = dimen + 2;
        int[][] result = new int[dimen][dimen];

        int[][] arr = new int[rDimen][rDimen];

        for (int k = 0; k < diamond; k++) {
            int i = ThreadLocalRandom.current().nextInt(1, rDimen - 1);
            int j = ThreadLocalRandom.current().nextInt(1, rDimen - 1);

            while (arr[i][j] == -1) {
                i = ThreadLocalRandom.current().nextInt(1, rDimen - 1);
                j = ThreadLocalRandom.current().nextInt(1, rDimen - 1);
            }
            arr[i][j] = -1;
        }

        for (int i = 1; i < arr.length - 1; i++) {
            for (int j = 1; j < arr.length - 1; j++) {
                if (arr[i][j] == -1) {
                    continue;
                }
                arr[i][j] = getValueCell(arr, i, j);
            }
        }

        for (int i = 1; i < arr.length - 1; i++) {
            for (int j = 1; j < arr.length - 1; j++) {
                result[i - 1][j - 1] = arr[i][j];
            }
        }

        return result;
    }

    private int getValueCell(int[][] arr, int i, int j) {
        for (int k = -1; k <= 1; k++) {
            for (int z = -1; z <= 1; z++) {
                if (arr[i + k][j + z] == -1) {
                    arr[i][j]++;
                }
            }
        }
        return arr[i][j];
    }
}

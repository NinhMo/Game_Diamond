package com.game_diamond.service.imp;

import com.game_diamond.entities.DailyLogin;
import com.game_diamond.entities.UserTurn;
import com.game_diamond.payload.response.DailyLoginRes;
import com.game_diamond.repository.DailyLoginRepo;
import com.game_diamond.service.DailyLoginService;
import com.game_diamond.service.UserService;
import com.game_diamond.service.UserTurnService;
import com.game_diamond.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class DailyLoginImp implements DailyLoginService {

    @Autowired
    private DailyLoginRepo dailyLoginRepo;

    @Autowired
    private UserTurnService userTurnService;

    @Override
    public Boolean isDailyLogin(Long userId) {
        Timestamp dbTimestamp;
        Timestamp currentTimestamp;

        Optional<DailyLogin> dailyLogin = dailyLoginRepo.findById(userId);

        if(!dailyLogin.isPresent()){
            DailyLogin newDailyLogin =new DailyLogin();
            newDailyLogin.setUserId(userId);
            newDailyLogin.setNewestDayLogin(TimeUtils.getCurrentTimestamp());
            dailyLoginRepo.save(newDailyLogin);
            return true;
        }
        dbTimestamp=dailyLogin.get().getNewestDayLogin();
        currentTimestamp=TimeUtils.getCurrentTimestamp();
        if (TimeUtils.timestamp1GreaterThanTimestamp2ByDate(currentTimestamp,dbTimestamp)){
            dailyLogin.get().setNewestDayLogin(currentTimestamp);
            dailyLoginRepo.save(dailyLogin.get());
            return true;
        }
        return false;
    }

    @Override
    public DailyLoginRes addTurnIfDailyLogin(Long userId) {
        if(isDailyLogin(userId)){
            UserTurn userTurn = userTurnService.createOrUpdate(userId, 3L);
            return new DailyLoginRes(true, userTurn.getTotalTurn());
        }
        return  new DailyLoginRes(false, null);
    }
}

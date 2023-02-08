package com.game_diamond.service.imp;

import com.game_diamond.entities.UserCoin;
import com.game_diamond.entities.UserTurn;
import com.game_diamond.payload.response.BuyTurnRes;
import com.game_diamond.repository.UserCoinRepo;
import com.game_diamond.repository.UserTurnRepo;
import com.game_diamond.service.UserCoinService;
import com.game_diamond.service.UserTurnService;
import com.game_diamond.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class UserTurnServiceImp implements UserTurnService {

    @Autowired
    private UserTurnRepo userTurnRepo;

    @Autowired
    private UserCoinService userCoinService;

    @Autowired
    private UserCoinRepo userCoinRepo;

    @Override
    public UserTurn createOrUpdate(Long userId, Long amountTurn) {

        Optional<UserTurn> userTurnOptional = userTurnRepo.findById(userId);
        if(userTurnOptional.isPresent()){
            Long updateTurn= userTurnOptional.get().getTotalTurn() + amountTurn;
            userTurnOptional.get().setTotalTurn(updateTurn);
            userTurnOptional.get().setUpdatesAt(TimeUtils.getCurrentTimestamp());
           return userTurnRepo.save(userTurnOptional.get());
        }
        UserTurn userTurn = new UserTurn();
         userTurn.setUserId(userId);
         userTurn.setTotalTurn(amountTurn);
         return userTurnRepo.save(userTurn);
    }

    @Override
    public BuyTurnRes buyTurnByCoin(Long userId, Long amountTurn) {
        Long toTalCoin = amountTurn*5;
        UserCoin userCoin = userCoinService.getInfo(userId);
        Long userTotalCoin = userCoin.getTotalCoin();
        if(userTotalCoin >= toTalCoin){
          UserTurn userTurn = createOrUpdate(userId, amountTurn);
          Long updateCoin = userTotalCoin-toTalCoin;
          UserCoin updateUserCoin = userCoinService.createOrUpdate(userId,-updateCoin);
          return new BuyTurnRes(userTurn.getTotalTurn(), updateUserCoin.getTotalCoin());
        }
        return null;
    }

    @Override
    public UserTurn findByUserId(Long userId) {
        Optional<UserTurn> userTurnOp = userTurnRepo.findById(userId);
        return userTurnOp.orElse(null);
    }

    @Override
    public UserTurn save(UserTurn userTurn) {
        return userTurnRepo.save(userTurn);
    }
}

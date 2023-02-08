package com.game_diamond.service.imp;

import com.game_diamond.entities.UserCoin;
import com.game_diamond.repository.UserCoinRepo;
import com.game_diamond.service.UserCoinService;
import com.game_diamond.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserCoinServiceImp implements UserCoinService {

    @Autowired
    private UserCoinRepo userCoinRepo;

    @Override
    public Optional<UserCoin> findByUserId(Long userId) {
        Optional<UserCoin> userCoinOptional = userCoinRepo.findByUserId(userId);
        if (!userCoinOptional.isPresent()){
            return userCoinOptional;
        }
        return Optional.empty();
    }

    @Override
    public UserCoin getInfo(Long userId) {
        Optional<UserCoin> userCoinOptional = userCoinRepo.findById(userId);
        if (userCoinOptional.isPresent()) {
           return userCoinOptional.get();
        }
        UserCoin userCoin = new UserCoin();
        userCoin.setUserId(userId);
        userCoin.setTotalCoin(0L);
        userCoin.setCreatedAt(TimeUtils.getCurrentTimestamp());
        return userCoinRepo.save(userCoin);

    }

    @Override
    public UserCoin createOrUpdate(Long userId, Long amountCoin) {
        Optional<UserCoin> userCoinOptional = userCoinRepo.findByUserId(userId);
        if (userCoinOptional.isPresent()){
            Long updateCoin = userCoinOptional.get().getTotalCoin()+amountCoin;
            userCoinOptional.get().setTotalCoin(updateCoin);
            userCoinOptional.get().setUpdatesAt(TimeUtils.getCurrentTimestamp());
            return userCoinRepo.save(userCoinOptional.get());
        }
        UserCoin userCoin = new UserCoin();
        userCoin.setUserId(userId);
        userCoin.setTotalCoin(amountCoin);
        userCoin.setCreatedAt(TimeUtils.getCurrentTimestamp());
        return userCoinRepo.save(userCoin);
    }
}


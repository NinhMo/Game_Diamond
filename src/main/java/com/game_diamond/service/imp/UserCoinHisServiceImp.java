package com.game_diamond.service.imp;

import com.game_diamond.entities.UserCoinHistory;
import com.game_diamond.repository.UserCoinHistoryRepo;
import com.game_diamond.service.UserCoinHisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCoinHisServiceImp implements UserCoinHisService {
    @Autowired
    private UserCoinHistoryRepo userCoinHistoryRepo;

    @Override
    public Page<UserCoinHistory> getUserCoinHisByUserId(Long userId, int page, int size) {
        return userCoinHistoryRepo.findByUserId(userId, PageRequest.of(page, size));
    }
}

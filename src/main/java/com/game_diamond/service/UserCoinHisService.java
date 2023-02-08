package com.game_diamond.service;


import com.game_diamond.entities.UserCoinHistory;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserCoinHisService {
    Page<UserCoinHistory> getUserCoinHisByUserId(Long userId, int page, int size);

}

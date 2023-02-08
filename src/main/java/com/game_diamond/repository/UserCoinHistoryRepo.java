package com.game_diamond.repository;

import com.game_diamond.entities.UserCoin;
import com.game_diamond.entities.UserCoinHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCoinHistoryRepo extends JpaRepository<UserCoinHistory, Long>{

    Page<UserCoinHistory> findByUserId(Long userId, Pageable pageable);
}

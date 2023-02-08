package com.game_diamond.repository;

import com.game_diamond.entities.UserCoin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCoinRepo extends JpaRepository<UserCoin,Long > {

    Optional<UserCoin> findByUserId(Long aLong);
}

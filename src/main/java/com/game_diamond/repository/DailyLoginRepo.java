package com.game_diamond.repository;

import com.game_diamond.entities.DailyLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DailyLoginRepo extends JpaRepository<DailyLogin, Long> {

}

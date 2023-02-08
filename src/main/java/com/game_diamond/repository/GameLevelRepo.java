package com.game_diamond.repository;

import com.game_diamond.entities.GameLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameLevelRepo extends JpaRepository<GameLevel, String> {
}

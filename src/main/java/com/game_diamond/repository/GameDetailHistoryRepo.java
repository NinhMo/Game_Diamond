package com.game_diamond.repository;

import com.game_diamond.entities.GameDetailHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameDetailHistoryRepo extends JpaRepository<GameDetailHistory, Long> {
}

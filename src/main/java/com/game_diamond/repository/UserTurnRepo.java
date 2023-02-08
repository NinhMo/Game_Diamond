package com.game_diamond.repository;

import com.game_diamond.entities.UserTurn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserTurnRepo extends JpaRepository<UserTurn,Long> {

    @Override
    Optional<UserTurn> findById(Long id);


}

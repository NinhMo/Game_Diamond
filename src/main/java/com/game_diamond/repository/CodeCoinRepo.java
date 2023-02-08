package com.game_diamond.repository;

import com.game_diamond.entities.CodeCoin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodeCoinRepo extends JpaRepository<CodeCoin, String> {

    Optional<CodeCoin> findByCode(String code);

    Boolean existsByCode(String code);

}

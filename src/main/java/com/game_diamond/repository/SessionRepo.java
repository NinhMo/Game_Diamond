package com.game_diamond.repository;

import com.game_diamond.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepo extends JpaRepository<Session,String> {
}

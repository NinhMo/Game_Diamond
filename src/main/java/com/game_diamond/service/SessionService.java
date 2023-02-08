package com.game_diamond.service;


import com.game_diamond.entities.Session;

public interface SessionService {
    Session create(Long userId);
    Session close(String ssessionId);
}

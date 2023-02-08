package com.game_diamond.service;

import com.game_diamond.entities.TopGameMonth;
import com.game_diamond.entities.TopGameWeek;
import com.game_diamond.payload.projection.TopGameMonthProj;
import com.game_diamond.payload.projection.TopGameProj;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopGameService {
    Page<TopGameProj> topGameByWeek(String weekCode, int page, int size);
    Page<TopGameMonthProj> topGameByMonth(String monthCode, int page, int size);
    void createOrUpdateTopWeekByWeekCode(Long userId, Long diamond, String weekCode);

    void createOrUpdateTopMonthByMonthCode(Long userId, Long diamond, String monthCode);
}

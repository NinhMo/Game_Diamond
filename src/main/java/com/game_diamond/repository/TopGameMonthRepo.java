package com.game_diamond.repository;

import com.game_diamond.entities.TopGameMonth;
import com.game_diamond.entities.TopGameWeek;
import com.game_diamond.payload.projection.TopGameMonthProj;
import com.game_diamond.payload.projection.TopGameProj;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TopGameMonthRepo extends JpaRepository<TopGameMonth, Long> {
//    Page<TopGameMonth> findByMonthCodeOrderByTotalDiamondDesc(String monthCode, Pageable pageable);

    @Query(value = "select user.username , top_game_month.total_diamond , top_game_month.month_code , top_game_month.created_at , top_game_month.update_at  from top_game_month, user \n" +
            "where month_code = ?1 and user.id = top_game_month.user_id order by top_game_month.total_diamond", nativeQuery = true)
    Page<TopGameMonthProj> getTopGameByMonth(String monthCode, Pageable pageable);

    Optional<TopGameMonth> findByUserIdAndMonthCode(Long userId, String monthCode);
}

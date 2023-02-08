package com.game_diamond.repository;

import com.game_diamond.entities.TopGameWeek;
import com.game_diamond.payload.projection.TopGameProj;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TopGameWeekRepo extends JpaRepository<TopGameWeek,Long> {
    Page<TopGameWeek> findByWeekCodeOrderByTotalDiamondDesc(String weekCode, Pageable pageable);

    @Query(value = "select user.username , total_diamond , week_code , top_game_week.created_at , top_game_week.update_at  from top_game_week, user \n" +
            "where week_code = ?1 and user.id = top_game_week.user_id order by top_game_week.total_diamond", nativeQuery = true)
    Page<TopGameProj> getTopGameByWeek(String weekCode, Pageable pageable);

    Optional<TopGameWeek> findByUserIdAndWeekCode(Long userId, String weekCode);
}

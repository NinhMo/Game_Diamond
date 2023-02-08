package com.game_diamond.entities;


import com.game_diamond.utils.TimeUtils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "GAME_DETAIL_HISTORY")
public class GameDetailHistory {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "SESSION_ID")
    private String sessionId;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "DIAMOND")
    private Long diamond;

    @Column(name = "GAME_LEVEL_CODE")
    private String gameLevelCode;

    @Column(name = "CREATED_AT")
    private Timestamp createdAt = TimeUtils.getCurrentTimestamp();

    @Column(name = "UPDATE_AT")
    private Timestamp updatesAt =  TimeUtils.getCurrentTimestamp();
}

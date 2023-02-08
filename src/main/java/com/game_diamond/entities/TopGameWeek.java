package com.game_diamond.entities;

import com.game_diamond.utils.TimeUtils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "TOP_GAME_WEEK")
public class TopGameWeek {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "TOTAL_DIAMOND")
    private Long totalDiamond;

    @Column(name = "WEEK_CODE")
    private String weekCode;

    @Column(name = "CREATED_AT")
    private Timestamp createdAt = TimeUtils.getCurrentTimestamp();

    @Column(name = "UPDATE_AT")
    private Timestamp updatesAt = TimeUtils.getCurrentTimestamp();
}

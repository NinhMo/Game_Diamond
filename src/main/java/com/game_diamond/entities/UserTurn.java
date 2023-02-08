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
@Table(name = "USER_TURN")
public class UserTurn {
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TOTAL_TURN")
    private  Long totalTurn;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "CREATED_AT")
    private Timestamp createdAt = TimeUtils.getCurrentTimestamp();

    @Column(name = "UPDATE_AT")
    private Timestamp updatesAt= TimeUtils.getCurrentTimestamp();


}

package com.game_diamond.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "TOP_GAME_MONTH")
public class TopGameMonth {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "TOTAL_DIAMOND")
    private Long totalDiamond;

    @Column(name = "MONTH_CODE")
    private String monthCode;

    @Column(name = "CREATED_AT")
    private Timestamp createdAt;

    @Column(name = "UPDATE_AT")
    private Timestamp updatesAt;

}

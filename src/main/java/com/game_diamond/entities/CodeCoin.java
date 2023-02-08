package com.game_diamond.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "CODE_COIN")
public class CodeCoin {

    @Id
    @Column(name = "CODE")
    private String code;

    @Column(name = "VALUE", nullable = false)
    private Long value;

    @Column(name = "IS_USED")
    private Boolean isUsed;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "CREATED_AT")
    private Timestamp createdAt;

    @Column(name = "UPDATE_AT")
    private Timestamp updatesAt;
}

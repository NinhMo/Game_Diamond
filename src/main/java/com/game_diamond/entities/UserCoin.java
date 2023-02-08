package com.game_diamond.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "USER_COIN")
public class UserCoin {
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TOTAL_COIN")
    private Long totalCoin;

    @Column(name = "USER_ID")
    private Long userId;

    @JsonIgnore
    @Column(name = "CREATED_AT")
    private Timestamp createdAt;

    @JsonIgnore
    @Column(name = "UPDATE_AT")
    private Timestamp updatesAt;

}

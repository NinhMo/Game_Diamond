package com.game_diamond.entities;

import com.game_diamond.utils.TimeUtils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "USER_COIN_HISTORY")
public class UserCoinHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "CODE_COIN")
    private String codeCoin;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "VALUE")
    private Long value;

    @Column(name = "CREATE_AT")
    private Timestamp createdAt = TimeUtils.getCurrentTimestamp();

    @Column(name ="UPDATE_AT")
    private Timestamp updateAt = TimeUtils.getCurrentTimestamp();
}

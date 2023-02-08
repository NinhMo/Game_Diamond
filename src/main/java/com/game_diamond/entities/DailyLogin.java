package com.game_diamond.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table (name = "DAYLY_LOGIN")
public class DailyLogin {
    @Id
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name="NEWEST_DAY_LOGIN")
    private Timestamp newestDayLogin;
}

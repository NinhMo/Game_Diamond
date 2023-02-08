package com.game_diamond.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "SESSION")
public class Session {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "BEGIN_TIME")
    private Timestamp beginTime;

    @Column(name = "END_TIME")
    private  Timestamp endTime;

    @Column(name = "USER_ID")
    private Long userId;
}

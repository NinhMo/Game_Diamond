package com.game_diamond.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name="USER")

public class User {
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME", nullable = false, unique = true)
    private String userName;

    @JsonIgnore
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "EMAIL", nullable = false,unique = true)
    private String email;

    @Column(name = "CREATED_AT")
    private Timestamp createdAt;

    @Column(name = "UPDATE_AT")
    private Timestamp updatesAt;
}

package com.game_diamond.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "GAME_LEVEL")
public class GameLevel {

    @Id
    @Column(name = "CODE")
    private String code;

    @Column(name = "NUMBER_OF_EDGES", nullable = false)
    private Long numberOfEdges;

    @Column(name = "NUMBERDIAMOND", nullable = false)
    private Long numberDiamond;


}

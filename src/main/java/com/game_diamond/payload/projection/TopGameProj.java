package com.game_diamond.payload.projection;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public interface TopGameProj {
    String getUsername();

    @JsonProperty("totalDiamond")
    Long getTotal_Diamond();

    @JsonProperty("weekCode")
    String getWeek_Code();

    @JsonProperty("createdAt")
    Timestamp getCreated_At();

    @JsonProperty("updateAt")
    Timestamp getUpdate_At();
}



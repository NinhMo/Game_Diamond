package com.game_diamond.payload.projection;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public interface TopGameMonthProj {
    String getUsername();

    @JsonProperty("totalDiamond")
    Long getTotal_Diamond();

    @JsonProperty("monthCode")
    String getMonth_Code();

    @JsonProperty("createdAt")
    Timestamp getCreated_At();

    @JsonProperty("updateAt")
    Timestamp getUpdate_At();

}

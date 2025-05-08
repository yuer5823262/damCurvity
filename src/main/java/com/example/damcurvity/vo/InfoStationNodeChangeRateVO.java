package com.example.damcurvity.vo;

import lombok.Data;

@Data
public class InfoStationNodeChangeRateVO {
    Integer stationId;
    String stationName;
    String node;
    Double xDiff;
    Double yDIff;
}

package com.example.damcurvity.que;

import lombok.Data;

@Data
public class InfoAlertQue extends BaseQue{
    String startTime;
    String endTime;
    String node;
    Integer stationId;
    Integer equId;
    String xyType;
    Integer state;

}

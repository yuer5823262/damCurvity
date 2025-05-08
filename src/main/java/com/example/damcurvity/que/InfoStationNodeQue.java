package com.example.damcurvity.que;

import lombok.Data;

import java.util.Date;

@Data
public class InfoStationNodeQue extends BaseQue{
    Integer interval;
    Integer displayNum;
    Integer stationId;
    Integer nodeName;
    String time;
    String initMark;

    public void setDisplayNumByInterval()
    {
        if(interval<60)
        {
            displayNum = 19;
        } else if (interval < 60 * 60) {
            displayNum = 16;
        } else if (interval < 24 * 60 * 60) {
            displayNum = 13;
        } else if (interval < 24 * 60 * 60 * 30) {
            displayNum = 10;
        }
        else {
            displayNum = 7;
        }
    }
}

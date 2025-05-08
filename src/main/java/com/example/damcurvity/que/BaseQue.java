package com.example.damcurvity.que;

import lombok.Data;

import java.util.Date;

@Data
public class BaseQue {
    Integer pageSize;
    Integer pageNum;
    String startTime;
    String endTime;
}

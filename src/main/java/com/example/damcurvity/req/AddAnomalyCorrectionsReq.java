package com.example.damcurvity.req;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.List;

@Data
public class AddAnomalyCorrectionsReq {
    @Excel(name = "stationId", width = 20)

    private Integer stationId;
    @Excel(name = "startTime", width = 20)

    private String startTime;
    @Excel(name = "endTime", width = 20)

    private String endTime;
    List<Integer> nodeList;
}

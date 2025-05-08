package com.example.damcurvity.excelVO;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
@Data
public class StationDataTemp {

    @Excel(name = "测点",width = 20)

    private Integer stationName;
    @Excel(name = "节点号",width = 20)

    private Integer nodeId;
    @Excel(name = "时间",width = 20)

    private Date time;
    @Excel(name = "xVal",width = 20)

    private Double xVal;
    @Excel(name = "yVal",width = 20)

    private Double yVal;
    @Excel(name = "zVal",width = 20)

    private Double zVal;
    @Excel(name = "xAcc",width = 20)

    private Double xAcc;
    @Excel(name = "yAcc",width = 20)

    private Double yAcc;
    @Excel(name = "zAcc",width = 20)

    private Double zAcc;
    @Excel(name = "voltage",width = 20)

    private Double voltage;
    @Excel(name = "温度",width = 20)

    private Double temp;
    @Excel(name = "角度",width = 20)

    private Double angle;
    @Excel(name = "mark",width = 20)

    private String mark;
}

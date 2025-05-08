package com.example.damcurvity.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * (InfoStaionNodeXz)表实体类
 *
 * @author makejava
 * @since 2023-10-09 20:56:18
 */
@Data
public class InfoStaionNodeXz extends InfoStationNode {
    @TableId(type = IdType.AUTO)
    @Excel(name = "id",width = 20)

    private Integer id;
    @Excel(name = "nodeId",width = 20)

    private Integer nodeId;
    @Excel(name = "stationId",width = 20)

    private Integer stationId;
    @Excel(name = "time",width = 20)

    private String time;
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
    @Excel(name = "temp",width = 20)

    private Double temp;
    @Excel(name = "angle",width = 20)

    private Double angle;
    @Excel(name = "state",width = 20)

    private Integer state;
    @Excel(name = "mark",width = 20)

    private String mark;

}


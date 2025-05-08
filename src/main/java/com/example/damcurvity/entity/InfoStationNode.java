package com.example.damcurvity.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * (InfoStationNode)表实体类
 *
 * @author makejava
 * @since 2023-08-05 23:43:13
 */
@Data
public class InfoStationNode extends Model<InfoStationNode> {
    public InfoStationNode()
    {

    }
    public InfoStationNode(List<String> str,String t)
    {
        nodeId= Integer.valueOf(str.get(0));
        time = t;
        voltage = Double.valueOf(str.get(1));
        temp = Double.valueOf(str.get(2));
        xAcc = Double.valueOf(str.get(3));
        yAcc = Double.valueOf(str.get(4));
        zAcc = Double.valueOf(str.get(5));
        xVal = Double.valueOf(str.get(6));
        yVal = Double.valueOf(str.get(7));
        zVal = Double.valueOf(str.get(8));
        angle = Double.valueOf(str.get(9));
    }

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
    public Double xVal;
    @Excel(name = "yVal",width = 20)
    public Double yVal;
    @Excel(name = "zVal",width = 20)
    public Double zVal;
    @Excel(name = "xAcc",width = 20)
    public Double xAcc;
    @Excel(name = "yAcc",width = 20)
    public Double yAcc;
    @Excel(name = "zAcc",width = 20)
    public Double zAcc;
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


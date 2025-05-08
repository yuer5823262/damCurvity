package com.example.damcurvity.excelVO;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * (StationInitValue)表实体类
 *
 * @author makejava
 * @since 2023-08-31 22:37:40
 */
@Data
public class StationInitValue extends Model<StationInitValue> {
    @Excel(name = "id",width = 20)

    private Integer id;
    @Excel(name = "stationId",width = 20)

    private Integer stationId;
    @Excel(name = "nodeId",width = 20)

    private Integer nodeId;
    @Excel(name = "time",width = 20)

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
    @Excel(name = "temp",width = 20)

    private Double temp;
    @Excel(name = "angle",width = 20)

    private Double angle;
    @Excel(name = "state",width = 20)

    private Integer state;
    @Excel(name = "mark",width = 20)

    private String mark;

}


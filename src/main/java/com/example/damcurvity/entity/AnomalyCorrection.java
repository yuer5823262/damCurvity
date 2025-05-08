package com.example.damcurvity.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;
import java.util.List;

import lombok.Data;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * (AnomalyCorrection)表实体类
 *
 * @author makejava
 * @since 2023-09-03 18:58:34
 */
@Data
public class AnomalyCorrection extends Model<AnomalyCorrection> {
    @TableId(type = IdType.AUTO)
    @Excel(name = "id", width = 20)

    private Integer id;
    @Excel(name = "nodeId", width = 20)

    private Integer nodeId;
    @Excel(name = "stationId", width = 20)

    private Integer stationId;
    @Excel(name = "startTime", width = 20)

    private String startTime;
    @Excel(name = "endTime", width = 20)

    private String endTime;
    @Excel(name = "correctionTime", width = 20)

    private String correctionTime;
    @Excel(name = "createTime", width = 20)

    private String createTime;
    @Excel(name = "operator", width = 20)

    private String operator;
    @Excel(name = "xVal", width = 20)

    private Double xVal;
    @Excel(name = "yVal", width = 20)

    private Double yVal;
    @Excel(name = "zVal", width = 20)

    private Double zVal;
    @Excel(name = "xAcc", width = 20)

    private Double xAcc;
    @Excel(name = "yAcc", width = 20)

    private Double yAcc;
    @Excel(name = "zAcc", width = 20)

    private Double zAcc;

    public void setAllVal(InfoStationNode startInfoStationNode, InfoStationNode endInfoStationNode) {
        xVal = endInfoStationNode.getXVal() - startInfoStationNode.getXVal();
        yVal = endInfoStationNode.getYVal() - startInfoStationNode.getYVal();
        zVal = endInfoStationNode.getZVal() - startInfoStationNode.getZVal();
        xAcc = endInfoStationNode.getXAcc() - startInfoStationNode.getXAcc();
        yAcc = endInfoStationNode.getYAcc() - startInfoStationNode.getYAcc();
        zAcc = endInfoStationNode.getZAcc() - startInfoStationNode.getZAcc();
//        for (InfoStationNode infoStationNode : infoStationNodeList) {
//            xVal+=infoStationNode.getXVal();
//            yVal+=infoStationNode.getYVal();
//            zVal+=infoStationNode.getZVal();
//            xAcc+=infoStationNode.getXAcc();
//            yAcc+=infoStationNode.getYAcc();
//            zAcc+=infoStationNode.getZAcc();
//        }
    }
}


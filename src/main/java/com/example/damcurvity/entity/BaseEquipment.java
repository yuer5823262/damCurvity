package com.example.damcurvity.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

import com.example.damcurvity.util.TimeUtils;
import lombok.Data;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * (BaseEquipment)表实体类
 *
 * @author makejava
 * @since 2023-09-02 11:01:59
 */
@Data
public class BaseEquipment extends Model<BaseEquipment> {
    @TableId(type = IdType.AUTO)
    @Excel(name = "id",width = 20)

    private Integer id;
    @Excel(name = "acquId",width = 20)

    private Integer acquId;
//    @Excel(name = "acquName",width = 20)
//
//    private String acquName;
    @Excel(name = "type",width = 20)

    private String type;
    @Excel(name = "name",width = 20)

    private String name;
    @Excel(name = "manufacturer",width = 20)

    private String manufacturer;
    @Excel(name = "monitorType",width = 20)

    private String monitorType;
    @Excel(name = "gatherType",width = 20)

    private String gatherType;
    @Excel(name = "hidden",width = 20)

    private Integer hidden;
    @Excel(name = "serialNumber",width = 20)

    private String serialNumber;
    @Excel(name = "lossThreshold",width = 20)

    private Double lossThreshold;
    @Excel(name = "volThreshold",width = 20)

    private Double volThreshold;
    @Excel(name = "firmwareVersion",width = 20)

    private String firmwareVersion;
    @Excel(name = "computeMode",width = 20)

    private String computeMode;
    @Excel(name = "arrangementMode",width = 20)

    private String arrangementMode;
    @Excel(name = "equation",width = 20)

    private String equation;
    @Excel(name = "deformationAzimuth",width = 20)

    private Double deformationAzimuth;
    @Excel(name = "markAzimuth",width = 20)

    private Double markAzimuth;
    @Excel(name = "netId",width = 20)

    private String netId;
    @Excel(name = "createTime",width = 20)

    private String createTime;
    @Excel(name = "operator",width = 20)

    private String operator;
    public  AlertInfo checkVol(InfoStationNode infoStationNode)
    {
        if(infoStationNode.getVoltage()==null) return null;
        double cj = infoStationNode.getVoltage() - volThreshold;
        if(cj < 0)
        {
            AlertInfo alertInfo = new AlertInfo();
            alertInfo.setPosition(this.serialNumber);
            alertInfo.setContent("设备"+this.name+"触发电压低值预警");
            alertInfo.setTime(TimeUtils.getNowTime());
            alertInfo.setLevel(4);
            return alertInfo;
        }
        else {
            return null;
        }
    }
}


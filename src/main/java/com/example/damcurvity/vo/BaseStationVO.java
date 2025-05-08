package com.example.damcurvity.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class BaseStationVO {
    @Excel(name = "id",width = 20)

    private Integer id;
    @Excel(name = "name",width = 20)

    private String name;
    @Excel(name = "projectMonitorId",width = 20)

    private Integer projectMonitorId;
    @Excel(name = "imgName",width = 20)

    private String imgName;
    @Excel(name = "monitorEquId",width = 20)

    private Integer monitorEquId;
    @Excel(name = "equName",width = 20)

    private String equName;
    @Excel(name = "cjqId",width = 20)

    private Integer cjqId;
    @Excel(name = "cjqName",width = 20)

    private String cjqName;
    @Excel(name = "isGetLocation",width = 20)

    private Integer isGetLocation;
    @Excel(name = "isAlert",width = 20)

    private Integer isAlert;
    @Excel(name = "jg",width = 20)

    private Integer jg;
    @Excel(name = "state",width = 20)

    private Integer state;
    @Excel(name = "createTime",width = 20)

    private String createTime;
    @Excel(name = "operaotr",width = 20)

    private String operaotr;

    @Excel(name = "longitudeLatitude",width = 20)
    //经纬度
    private String longitudeLatitude;
    @Excel(name = "damPosition",width = 20)
    //起始位置
    private String damPosition;

}

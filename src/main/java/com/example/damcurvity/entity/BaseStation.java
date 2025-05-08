package com.example.damcurvity.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * (BaseStation)表实体类
 *
 * @author makejava
 * @since 2023-10-04 22:20:04
 */
@Data
public class BaseStation extends Model<BaseStation> {
    @TableId(type = IdType.AUTO)
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
    @Excel(name = "isGetLocation",width = 20)

    private Integer isGetLocation;
    @Excel(name = "isAlert",width = 20)

    private Integer isAlert;
    @Excel(name = "jg",width = 20)

    private Integer jg;
    @Excel(name = "equDirection",width = 20)

    private String equDirection;
    @Excel(name = "verticalLabel",width = 20)

    private String verticalLabel;
    @Excel(name = "refElevation",width = 20)

    private Double refElevation;
    @Excel(name = "coordinateLabel",width = 20)

    private String coordinateLabel;
    @Excel(name = "diagramLeft",width = 20)

    private String diagramLeft;
    @Excel(name = "diagramRight",width = 20)

    private String diagramRight;
    @Excel(name = "state",width = 20)

    private Integer state;
    @Excel(name = "createTime",width = 20)

    private String createTime;
    @Excel(name = "operator",width = 20)

    private String operator;
    @Excel(name = "diagramLeftLeftMark",width = 20)

    private String diagramLeftLeftMark;
    @Excel(name = "diagramLeftRightMark",width = 20)

    private String diagramLeftRightMark;
    @Excel(name = "diagramRightLeftMark",width = 20)

    private String diagramRightLeftMark;
    @Excel(name = "diagramRightRightMark",width = 20)

    private String diagramRightRightMark;
    @Excel(name = "trajectoryLeftMark",width = 20)

    private String trajectoryLeftMark;
    @Excel(name = "trajectoryRightMark",width = 20)

    private String trajectoryRightMark;
    @Excel(name = "exceptionScreeningInterval",width = 20)

    private Integer exceptionScreeningInterval;
    @Excel(name = "confidenceInterval",width = 20)

    private Integer confidenceInterval;
    @Excel(name = "filterInterval",width = 20)

    private Integer filterInterval;
    @Excel(name = "filterLevel",width = 20)

    private Integer filterLevel;
    @Excel(name = "longitudeLatitude",width = 20)
    //经纬度
    private String longitudeLatitude;
    @Excel(name = "damPosition",width = 20)
    //起始位置
    private String damPosition;

}


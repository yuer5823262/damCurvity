package com.example.damcurvity.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * (BaseProjectMonitorType)表实体类
 *
 * @author makejava
 * @since 2023-08-05 23:43:12
 */
@Data
public class BaseProjectMonitorType extends Model<BaseProjectMonitorType> {
    @TableId(type = IdType.AUTO)
    @Excel(name = "id",width = 20)

    private Integer id;
    @Excel(name = "name",width = 20)

    private String name;
    @Excel(name = "monitorType",width = 20)

    private String monitorType;
    @Excel(name = "equipmentType",width = 20)

    private String equipmentType;
    @Excel(name = "transAttr",width = 20)

    private String transAttr;
    @Excel(name = "equation",width = 20)

    private String equation;
    @Excel(name = "remark",width = 20)

    private String remark;
    @Excel(name = "createTime",width = 20)

    private String createTime;
    @Excel(name = "operator",width = 20)

    private String operator;

}


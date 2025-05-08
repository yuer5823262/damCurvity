package com.example.damcurvity.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * (BaseProject)表实体类
 *
 * @author makejava
 * @since 2023-08-05 23:43:12
 */
@Data
public class BaseProject extends Model<BaseProject> {
    @TableId(type = IdType.AUTO)
    @Excel(name = "id",width = 20)

    private Integer id;
    @Excel(name = "name",width = 20)

    private String name;
    @Excel(name = "info",width = 20)

    private String info;
    @Excel(name = "addr",width = 20)

    private String addr;
    @Excel(name = "longitudeLatitude",width = 20)

    private String longitudeLatitude;
    @Excel(name = "imgName",width = 20)

    private String imgName;
    @Excel(name = "monitor",width = 20)

    private Integer monitor;
    @Excel(name = "createTime",width = 20)

    private String createTime;
    @Excel(name = "operator",width = 20)

    private String operator;


    @Excel(name = "dam_height",width = 20)

    private Double damHeight;

}


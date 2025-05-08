package com.example.damcurvity.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * (BaseStationNode)表实体类
 *
 * @author makejava
 * @since 2023-10-05 09:53:07
 */
@Data
public class BaseStationNode {
    @TableId(type = IdType.AUTO)
    @Excel(name = "id",width = 20)

    private Integer id;
    @Excel(name = "stationId",width = 20)

    private Integer stationId;
    @Excel(name = "name",width = 20)

    private String name;
    @Excel(name = "distance",width = 20)

    private Integer distance;

}


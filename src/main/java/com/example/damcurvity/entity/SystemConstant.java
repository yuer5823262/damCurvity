package com.example.damcurvity.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * (SystemConstant)表实体类
 *
 * @author makejava
 * @since 2023-08-20 08:45:28
 */
@Data
public class SystemConstant extends Model<SystemConstant> {
    @TableId(type = IdType.AUTO)
    @Excel(name = "id",width = 20)

    private Integer id;
    @Excel(name = "type",width = 20)

    private String type;
    @Excel(name = "val",width = 20)

    private String val;

}


package com.example.damcurvity.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * (Role)表实体类
 *
 * @author makejava
 * @since 2023-09-24 16:14:01
 */
@Data
public class Role extends Model<Role> {
    @TableId(type = IdType.AUTO)
    @Excel(name = "id",width = 20)

    private Integer id;
    @Excel(name = "roleName",width = 20)

    private String roleName;
    @Excel(name = "createTime",width = 20)

    private String createTime;
    @Excel(name = "operator",width = 20)

    private String operator;
    @Excel(name = "projectIdList",width = 20)

    private String projectIdList;
    @Excel(name = "roleInfo",width = 20)

    private String roleInfo;
    @Excel(name = "mark1",width = 20)

    private String mark1;
    @Excel(name = "mark2",width = 20)

    private String mark2;

}


package com.example.damcurvity.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * (BaseUser)表实体类
 *
 * @author makejava
 * @since 2023-08-05 23:43:13
 */
@Data
public class BaseUser extends Model<BaseUser> {
    @TableId(type = IdType.AUTO)
    @Excel(name = "id",width = 20)

    private Integer id;
    @Excel(name = "username",width = 20)

    private String username;
    @Excel(name = "name",width = 20)

    private String name;
    @Excel(name = "pwd",width = 20)

    private String pwd;
    @Excel(name = "gender",width = 20)

    private Integer gender;
    @Excel(name = "organization",width = 20)

    private String organization;
    @Excel(name = "department",width = 20)

    private String department;
    @Excel(name = "position",width = 20)

    private String position;
    @Excel(name = "phone",width = 20)

    private String phone;
    @Excel(name = "type",width = 20)

    private Integer type;
    @Excel(name = "role",width = 20)

    private Integer role;
    @Excel(name = "createTime",width = 20)

    private String createTime;
    @Excel(name = "operator",width = 20)

    private String operator;

}


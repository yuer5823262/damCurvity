package com.example.damcurvity.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * (UserLog)表实体类
 *
 * @author makejava
 * @since 2023-08-05 23:43:14
 */
@Data
public class UserLog extends Model<UserLog> {
    @TableId(type = IdType.AUTO)
    @Excel(name = "id",width = 20)

    private Integer id;
    @Excel(name = "operator",width = 20)

    private String operator;
    @Excel(name = "type",width = 20)

    private String type;
    @Excel(name = "time",width = 20)

    private String time;
    @Excel(name = "ipAddr",width = 20)

    private String ipAddr;

}


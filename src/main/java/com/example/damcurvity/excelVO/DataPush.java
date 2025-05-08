package com.example.damcurvity.excelVO;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * (DataPush)表实体类
 *
 * @author makejava
 * @since 2023-08-27 09:19:04
 */
@Data
public class DataPush extends Model<DataPush> {
    @Excel(name = "id",width = 20)

    private Integer id;
    @Excel(name = "type",width = 20)

    private String type;
    @Excel(name = "url",width = 20)

    private String url;
    @Excel(name = "jg",width = 20)

    private Integer jg;
    @Excel(name = "state",width = 20)

    private Integer state;
    @Excel(name = "mark",width = 20)

    private String mark;
    @Excel(name = "projectId",width = 20)

    private Integer projectId;
    @Excel(name = "createTime",width = 20)

    private String createTime;
    @Excel(name = "operator",width = 20)

    private String operator;

}


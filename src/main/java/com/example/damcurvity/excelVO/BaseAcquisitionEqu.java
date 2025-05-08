package com.example.damcurvity.excelVO;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * (BaseAcquisitionEqu)表实体类
 *
 * @author makejava
 * @since 2023-08-25 16:01:50
 */
@Data
public class BaseAcquisitionEqu extends Model<BaseAcquisitionEqu> {
    @Excel(name = "id",width = 20)

    private Integer id;
    @Excel(name = "projectId",width = 20)

    private Integer projectId;
    @Excel(name = "name",width = 20)

    private String name;
    @Excel(name = "dk",width = 20)

    private Integer dk;
    @Excel(name = "createTime",width = 20)

    private String createTime;
    @Excel(name = "operator",width = 20)

    private String operator;

}


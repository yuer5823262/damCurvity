package com.example.damcurvity.excelVO;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * 报警信息(AlertInfo)表实体类
 *
 * @author makejava
 * @since 2025-04-13 19:46:40
 */
@Data
public class AlertInfo extends Model<AlertInfo> {
    @Excel(name = "id",width = 20)

    private Integer id;
    @Excel(name = "position",width = 20)
    //位置
    private String position;
    @Excel(name = "node",width = 20)
    //节点
    private String node;
    @Excel(name = "xyType",width = 20)
    //xy方向
    private String xyType;
    @Excel(name = "type",width = 20)
    //类型
    private String type;
    @Excel(name = "level",width = 20)
    //等级
    private Integer level;
    @Excel(name = "typeNo",width = 20)
    //类别号
    private Integer typeNo;
    @Excel(name = "content",width = 20)
    //内容
    private String content;
    @Excel(name = "time",width = 20)
    //时间
    private String time;
    @Excel(name = "state",width = 20)
    //状态
    private Integer state;
    @Excel(name = "remark1",width = 20)
    //反评1
    private String remark1;
    @Excel(name = "remark2",width = 20)
    //反评2
    private String remark2;
    @Excel(name = "remark3",width = 20)
    //反评3
    private String remark3;
    @Excel(name = "opTime1",width = 20)
    //处理时间1
    private String opTime1;
    @Excel(name = "operator1",width = 20)
    //处理人1
    private String operator1;
    @Excel(name = "opTime2",width = 20)
    //处理时间2
    private String opTime2;
    @Excel(name = "operator2",width = 20)
    //处理人2
    private String operator2;
    @Excel(name = "opTime3",width = 20)
    //处理时间3
    private String opTime3;
    @Excel(name = "operator3",width = 20)
    //处理人3
    private String operator3;

}


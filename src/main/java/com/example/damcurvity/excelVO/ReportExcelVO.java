package com.example.damcurvity.excelVO;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class ReportExcelVO {
    @Excel(name = "节点",width = 20)
    String node;
    @Excel(name = "时间",width = 20)
    String nodeTime;
    @Excel(name = "X值",width = 20)
    Double xVal;
    @Excel(name = "Y值",width = 20)
    Double yVal;

}

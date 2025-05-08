package com.example.damcurvity.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.damcurvity.util.TimeUtils;
import com.example.damcurvity.vo.InfoStationNodeChangeRateVO;
import lombok.Data;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * 报警管理(BaseAlertManager)表实体类
 *
 * @author makejava
 * @since 2025-04-10 20:34:33
 */
@Data
public class BaseAlertManager extends Model<BaseAlertManager> {
    @TableId(type = IdType.AUTO)
    @Excel(name = "id",width = 20)

    private Integer id;
    @Excel(name = "stationId",width = 20)
    //测点id
    private Integer stationId;
    @Excel(name = "monitorValType",width = 20)
    //监测数据类型
    private String monitorValType;
    @Excel(name = "monitorType",width = 20)
    //监测类型
    private Integer monitorType;
    @Excel(name = "blue1",width = 20)
    //变化速率蓝色阈值
    private Double blue1;
    @Excel(name = "yellow1",width = 20)
    //变化速率换色阈值
    private Double yellow1;
    @Excel(name = "orange1",width = 20)
    //变化速率橙色阈值
    private Double orange1;
    @Excel(name = "red1",width = 20)
    //变化速率红色阈值
    private Double red1;
    @Excel(name = "blue2",width = 20)
    //变化蓝色阈值
    private Double blue2;
    @Excel(name = "yellow2",width = 20)
    //变化黄色阈值
    private Double yellow2;
    @Excel(name = "orange2",width = 20)
    //变化橙色阈值
    private Double orange2;
    @Excel(name = "red2",width = 20)
    //变化红色阈值
    private Double red2;
    @Excel(name = "createTime",width = 20)
    //创建时间
    private String createTime;
    @Excel(name = "operator",width = 20)
    //创建人
    private String operator;
    @Excel(name = "blue11",width = 20)
    //变化速率蓝色阈值
    private Double blue11;
    @Excel(name = "yellow11",width = 20)
    //变化速率换色阈值
    private Double yellow11;
    @Excel(name = "orange11",width = 20)
    //变化速率橙色阈值
    private Double orange11;
    @Excel(name = "red11",width = 20)
    //变化速率红色阈值
    private Double red11;
    @Excel(name = "blue22",width = 20)
    //变化蓝色阈值
    private Double blue22;
    @Excel(name = "yellow22",width = 20)
    //变化黄色阈值
    private Double yellow22;
    @Excel(name = "orange22",width = 20)
    //变化橙色阈值
    private Double orange22;
    @Excel(name = "red22",width = 20)
    //变化红色阈值
    private Double red22;
    public List<AlertInfo> checkAlert(List<StationInitValue> stationInitValue, List<InfoStationNode> infoStationNodes, BaseStation baseStation) {
        List<AlertInfo> alertInfoList = new ArrayList<>();

        for (InfoStationNode infoStationNode : infoStationNodes) {
            for (StationInitValue initValue : stationInitValue) {
                if (Objects.equals(initValue.getNodeId(), infoStationNode.getNodeId()))
                {
                    boolean mark = false;
                    AlertInfo alertInfo = new AlertInfo();
                    alertInfo.setTime(TimeUtils.getNowTime());
                    alertInfo.setState(0);
                    alertInfo.setXyType("x");
                    double cj = Math.abs(infoStationNode.getXVal() - initValue.getXVal());
                    if(cj >= this.red1)
                    {
                        mark = true;
                        alertInfo.setLevel(4);
                    } else if (cj >= this.orange1) {
                        mark = true;
                        alertInfo.setLevel(3);
                    }else if (cj >= this.yellow1) {
                        mark = true;
                        alertInfo.setLevel(2);
                    }else if (cj >= this.blue1) {
                        mark = true;
                        alertInfo.setLevel(1);
                    }
                    if(mark){
                        alertInfo.setPosition(baseStation.getName());
                        alertInfo.setNode(infoStationNode.getNodeId()+"");
                        alertInfo.setContent("测点"+baseStation.getName()+"的"+infoStationNode.getNodeId()+"节点x方向发生累计变化预警，变化量为"+cj);
                        alertInfoList.add(alertInfo);
                    }


                    mark = false;
                    alertInfo = new AlertInfo();
                    alertInfo.setTime(TimeUtils.getNowTime());
                    alertInfo.setState(0);
                    alertInfo.setPosition(infoStationNode.getNodeId()+"");
                    cj = Math.abs(infoStationNode.getYVal() - initValue.getYVal());
                    if(cj >= this.red11)
                    {
                        mark = true;
                        alertInfo.setLevel(4);
                    } else if (cj >= this.orange11) {
                        mark = true;
                        alertInfo.setLevel(3);
                    }else if (cj >= this.yellow11) {
                        mark = true;
                        alertInfo.setLevel(2);
                    }else if (cj >= this.blue11) {
                        mark = true;
                        alertInfo.setLevel(1);
                    }
                    if(mark){
                        alertInfo.setXyType("y");
                        alertInfo.setPosition(baseStation.getName());
                        alertInfo.setNode(infoStationNode.getNodeId()+"");
                        alertInfo.setContent("测点"+baseStation.getName()+"的"+infoStationNode.getNodeId()+"节点y方向发生累计变化预警，变化量为"+cj);
                        alertInfoList.add(alertInfo);
                    }
                }
            }
        }

        return alertInfoList;
    }

    public AlertInfo checkChangeAlertX(InfoStationNodeChangeRateVO infoStationNodeChangeRateVO) {
        AlertInfo alertInfo = new AlertInfo();
        alertInfo.setTime(TimeUtils.getNowTime());
        alertInfo.setState(0);
        boolean mark = false;
        alertInfo.setXyType("x");
        alertInfo.setPosition(infoStationNodeChangeRateVO.getStationName());
        alertInfo.setNode(infoStationNodeChangeRateVO.getNode());
        double cj;
        cj = Math.abs(infoStationNodeChangeRateVO.getXDiff());

        if(cj >= this.red2)
        {
            mark = true;
            alertInfo.setLevel(4);
        } else if (cj >= this.orange2) {
            mark = true;
            alertInfo.setLevel(3);
        }else if (cj >= this.yellow2) {
            mark = true;
            alertInfo.setLevel(2);
        }else if (cj >= this.blue2) {
            mark = true;
            alertInfo.setLevel(1);
        }
        if(mark){
            alertInfo.setContent("测点"+infoStationNodeChangeRateVO.getStationName()+"的"+infoStationNodeChangeRateVO.getNode()+"节点X方向发生变化速率预警，变化量为"+cj);
            return alertInfo;
        }
        return null;
    }


    public AlertInfo checkChangeAlertY(InfoStationNodeChangeRateVO infoStationNodeChangeRateVO) {
        AlertInfo alertInfo = new AlertInfo();
        alertInfo.setTime(TimeUtils.getNowTime());
        alertInfo.setState(0);
        alertInfo.setXyType("y");
        boolean mark = false;
        alertInfo.setPosition(infoStationNodeChangeRateVO.getStationName());
        alertInfo.setNode(infoStationNodeChangeRateVO.getNode());
        double cj;
        cj = Math.abs(infoStationNodeChangeRateVO.getYDIff());
        if(cj >= this.red22)
        {
            mark = true;
            alertInfo.setLevel(4);
        } else if (cj >= this.orange22) {
            mark = true;
            alertInfo.setLevel(3);
        }else if (cj >= this.yellow22) {
            mark = true;
            alertInfo.setLevel(2);
        }else if (cj >= this.blue22) {
            mark = true;
            alertInfo.setLevel(1);
        }
        if(mark){
            alertInfo.setContent("测点"+infoStationNodeChangeRateVO.getStationName()+"的"+infoStationNodeChangeRateVO.getNode()+"节点Y方向发生变化速率预警，变化量为"+cj);
            return alertInfo;
        }
        return null;
    }
}


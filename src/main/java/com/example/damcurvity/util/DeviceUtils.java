package com.example.damcurvity.util;

import com.example.damcurvity.common.Constant;
import com.example.damcurvity.entity.BaseStation;
import com.example.damcurvity.entity.BaseStationNode;
import com.example.damcurvity.entity.InfoStationNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class DeviceUtils {
    private static int delayTime=1000;
    private final SerialPortUtils serialPortUtils;
    public static final ConcurrentHashMap<String,DeviceUtils> deviceUtilsConcurrentHashMap = new ConcurrentHashMap<>();
    private DeviceUtils(String port)
    {
        this.serialPortUtils = new SerialPortUtils(port);
    }
    public synchronized static DeviceUtils getDeviceUtil(Integer port)
    {
        String p = "com"+port;
        DeviceUtils deviceUtils= deviceUtilsConcurrentHashMap.get(p);
        if(deviceUtils!=null) {
            return deviceUtils;
        }
        else {
            deviceUtils= new DeviceUtils(p);
            deviceUtilsConcurrentHashMap.put(p,deviceUtils);
            return deviceUtils;
        }
    }
    public synchronized boolean portIsOpen()
    {
        return serialPortUtils.portIsOpen();
    }
    public synchronized  List<InfoStationNode> getDatas(String equNumber)
    {

        List<InfoStationNode> result = new ArrayList<>();
        serialPortUtils.readAllData();
        String cmd = "HUASI,GET,DATA,"+equNumber;
        serialPortUtils.sendData(cmd);
        try {
                Thread.sleep(delayTime);
            } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String temp = serialPortUtils.receiveData();

        if (temp == null) return null;
        this.oK();
        Constant.logger.info(equNumber+"获取到设备数据:"+temp);
        temp = temp.substring(1, temp.length()-5);
        List<String> strings = Arrays.asList(temp.split(","));
        strings = strings.subList(5,strings.size());
        for(int i = 0;i<strings.size();i+=10)
        {
            InfoStationNode infoStationNode = new InfoStationNode(strings.subList(i,i+10),TimeUtils.getNowTime());
            result.add(infoStationNode);
        }
        return result;
    }


    public synchronized  InfoStationNode getData(String equNumber,String node)
    {
        try {
            serialPortUtils.readAllData();
            String cmd = "HUASI,GET,DATA,"+equNumber+","+node;
            serialPortUtils.sendData(cmd);
            try {
                Thread.sleep(delayTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String temp = serialPortUtils.receiveData();

            if (temp == null) return null;
            temp = temp.substring(1, temp.length()-8);
            Constant.logger.info(equNumber+" N:"+node+"获取到设备数据:"+temp);
            List<String> strings = Arrays.asList(temp.split(","));
            strings = strings.subList(5,strings.size());
            InfoStationNode infoStationNode = new InfoStationNode(strings,TimeUtils.getNowTime());
            return infoStationNode;
        }catch (Exception ignored)
        {
            Constant.logger.error("节点数据采集错误"+equNumber+":::"+node);
            return null;
        }
    }

    public synchronized  List<String> getModel(String equNumber)
    {

        List<InfoStationNode> result = new ArrayList<>();
        serialPortUtils.readAllData();
        String cmd = "HUASI,GET,MODEL,"+equNumber;
        serialPortUtils.sendData(cmd);
        try {
            Thread.sleep(delayTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String temp = serialPortUtils.receiveData();

        if (temp == null) return null;
        this.oK();
        Constant.logger.info(equNumber+"获取到设备模式:"+temp);
        temp = temp.substring(1, temp.length()-5);
        List<String> strings = Arrays.asList(temp.split(","));
        strings = strings.subList(4,6);
        return strings;
    }
    public synchronized Boolean setModel(String equNumber,String model1,String model2)
    {
        serialPortUtils.readAllData();

        String cmd = "HUASI,SET,MODEL,"+equNumber+","+model1+","+model2;
        serialPortUtils.sendData(cmd);
        try {
            Thread.sleep(delayTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String temp = serialPortUtils.receiveData();
        if (temp == null) return false;
        temp = temp.substring(1, temp.length()-5);
        return checkLastOk(temp);
    }

    public synchronized List<BaseStationNode> getNodesByDeviceNumber(String equNumber)
    {
        serialPortUtils.readAllData();
        String cmd = "HUASI,GET,DEVICE,"+equNumber;
        List<BaseStationNode> result = new ArrayList<>();
        serialPortUtils.sendData(cmd);
        try {
            Thread.sleep(delayTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String temp = serialPortUtils.receiveData();
        if (temp == null) return null;
        Constant.logger.info(equNumber+"获取到节点号:"+temp);
        temp = temp.substring(1, temp.length()-5);
        List<String> strings = Arrays.asList(temp.split(","));
        strings = strings.subList(6,strings.size()-1);
        for(int i = 0;i<strings.size();i+=2)
        {
            BaseStationNode baseStationNode = new BaseStationNode();
            baseStationNode.setName(strings.get(i));
            baseStationNode.setDistance(1000);
            result.add(baseStationNode);
        }
        return result;
    }

    public synchronized Boolean setUploadModel(String equNumber,String model)
    {
        serialPortUtils.readAllData();

        String cmd = "HUASI,SET,UPLOADMODEL,"+equNumber+","+model;
        serialPortUtils.sendData(cmd);
        try {
            Thread.sleep(delayTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String temp = serialPortUtils.receiveData();
        if (temp == null) return false;
        temp = temp.substring(1, temp.length()-5);
        return checkLastOk(temp);
    }

    public synchronized String getVersion()
    {
        serialPortUtils.readAllData();
        String cmd = "HUASI,GET,VERSION";
        serialPortUtils.sendData(cmd);
        try {
            Thread.sleep(delayTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String temp = serialPortUtils.receiveData();
        if (temp == null) return null;
        temp = temp.substring(1, temp.length()-5);
        if(!checkLastOk(temp)) return null;
        List<String> strings = Arrays.asList(temp.split(","));
        return strings.get(3);
    }

    public synchronized boolean reGetCall()
    {
        serialPortUtils.readAllData();
        String cmd = "HUASI,SET,GETCA";
        serialPortUtils.sendData(cmd);
        try {
            Thread.sleep(delayTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String temp = serialPortUtils.receiveData();
        if (temp == null) return false;
        temp = temp.substring(1, temp.length()-5);
        return checkLastOk(temp);
    }

    public synchronized boolean setSave()
    {
        serialPortUtils.readAllData();
        String cmd = "HUASI,SET,SAVE";
        serialPortUtils.sendData(cmd);
        try {
            Thread.sleep(delayTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String temp = serialPortUtils.receiveData();
        if (temp == null) return false;
        temp = temp.substring(1, temp.length()-5);
        return checkLastOk(temp);
    }

    public synchronized void reset()
    {
        String cmd = "HUASI,SET,RESET";
        serialPortUtils.sendData(cmd);
    }
    public synchronized void oK()
    {
        String cmd = "$HUASI,OK";
        serialPortUtils.sendData(cmd);
    }
    public synchronized String sendMessageAndGetResult(String cmd)
    {
        serialPortUtils.readAllData();
        serialPortUtils.sendData(cmd);
        try {
            Thread.sleep(delayTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String temp = serialPortUtils.receiveData();
        if (temp == null) return null;
        temp = temp.substring(1, temp.length()-5);
        return temp;
    }


    private boolean checkLastOk(String str) {
        if (str.length() < 2) {
            return false;
        }

        String lastTwoCharacters = str.substring(str.length() - 2);

        return lastTwoCharacters.equals("OK");
    }
}

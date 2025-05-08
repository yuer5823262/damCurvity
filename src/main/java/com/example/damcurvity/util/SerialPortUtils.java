package com.example.damcurvity.util;

import com.example.damcurvity.common.Constant;
import com.fazecast.jSerialComm.SerialPort;

import java.io.IOException;
import java.io.InputStream;

public class SerialPortUtils {
    private  SerialPort serialPort;
    SerialPortUtils(String port)
    {
        this.init(port,115200);
    }
    private  String checkStr(String str)
    {
        int checksum = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            checksum ^= c;
        }

        String hexChecksum = Integer.toHexString(checksum).toUpperCase();
        return hexChecksum;
    }
    public synchronized  Boolean portIsOpen()
    {
        return serialPort != null && serialPort.isOpen();
    }
    // 初始化串口
    public synchronized void init(String portName, int baudRate) {
        serialPort = SerialPort.getCommPort(portName);
        serialPort.setBaudRate(baudRate);
        if (serialPort.openPort()) {
            Constant.logger.info("Serial port opened successfully "+portName);
        } else {
            Constant.logger.info("Error: Failed to open serial port.");
        }
    }

    // 发送数据
    public synchronized  void sendData(String data) {
        data = "$"+data+"*"+checkStr(data)+"\r\n";
        Constant.logger.info(data);
        byte[] cmd = data.getBytes();
        if(serialPort==null|| !serialPort.isOpen())
        {
            return;
        }
        int i = serialPort.writeBytes(cmd, cmd.length);
        if(i==-1)
        {
            Constant.logger.info("命令发送失败");
        }
    }

    // 接收数据
    public synchronized  String receiveData() {
        String result = null;
        if(serialPort==null||!serialPort.isOpen())
        {
            return null;
        }
        try {
            InputStream inputStream = serialPort.getInputStream();
            byte[] buffer = new byte[2048];
            int len = -1;
            len = inputStream.read(buffer);
            result = new String(buffer, 0, len);

        } catch (IOException e) {
            Constant.logger.error("没有读取到数据",e);
        }
        return result;
    }


    public synchronized String readAllData() {
        StringBuilder result = new StringBuilder();

        if (serialPort == null || !serialPort.isOpen()) {
            return null;
        }

        try {
            InputStream inputStream = serialPort.getInputStream();
            byte[] buffer = new byte[2048];
            int len;

            while ((len = inputStream.read(buffer)) != -1) {
                result.append(new String(buffer, 0, len));
            }
        } catch (IOException ignored) {

        }

        return result.toString();
    }



    // 关闭串口
    public synchronized  void close() {
        if (serialPort != null && serialPort.isOpen()) {
            serialPort.closePort();
            System.out.println("Serial port closed successfully.");
        }
    }


}

package com.example.damcurvity.common;

import com.example.damcurvity.util.DeviceUtils;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint(value = "/api/websocket/{port}")
public class WebsocketServerEndpoint {
    //在线连接数,应该把它设计成线程安全的
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    //虽然@Component默认是单例模式的，但springboot还是会为每个websocket连接初始化一个bean，所以可以用一个静态set保存起来。
    public static CopyOnWriteArraySet<WebsocketServerEndpoint> websocketServerSet
            = new CopyOnWriteArraySet<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //会话窗口的ID标识
    private Integer p;

    /**
     * 链接成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("port") Integer port) {
        Constant.logger.info("onOpen >> 链接成功");
        this.session = session;
        //将当前websocket对象存入到Set集合中
        websocketServerSet.add(this);
        //在线人数+1
        addOnlineCount();
        Constant.logger.info("有新窗口开始监听：" + p + ", 当前在线人数为：" + getOnlineCount());
        this.p = port;
        try {
            sendMessage("start*************************************************************************");
        } catch (Exception e) {
            Constant.logger.error("websocket 发送失败",e);
        }


    }
    /**
     * 链接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        Constant.logger.info("onClose >> 链接关闭");
        //移除当前Websocket对象
        websocketServerSet.remove(this);
        //在内线人数-1
        subOnLineCount();
    }
    /**
     * 收到客户端消息后调用的方法
     *
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message) {
        Constant.logger.info("接收到串口：" + this.p + " 的信息：" + message);
        DeviceUtils deviceUtils = DeviceUtils.getDeviceUtil(this.p);
        String result = deviceUtils.sendMessageAndGetResult(message);
        if(result==null)
        {
            this.sendMessage("错误");
            return;
        }
        Constant.logger.info("返回串口：" + this.p + " 的信息：" + result);
        this.sendMessage(result);
    }

    @OnError
    public void onError(Session session, Throwable e) {
        Constant.logger.error("错误:",e);
    }
    /**
     * 推送消息
     *
     * @param message
     */
    public  void sendMessage(String message) {
        try {
            synchronized(this.session)
            {
                this.session.getBasicRemote().sendText(message);
            }

        } catch (IOException e) {
            Constant.logger.error("错误:",e);
        }
    }

    private void subOnLineCount() {
        WebsocketServerEndpoint.onlineCount--;
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    private void addOnlineCount() {
        WebsocketServerEndpoint.onlineCount++;
    }


}

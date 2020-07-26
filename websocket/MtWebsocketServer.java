package com.example.websocket;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Create by jackey
 * On 2020/5/1
 */
@ServerEndpoint("/mtws/{deviceCode}")
@Component
public class MtWebsocketServer {
    protected final Logger logger = LoggerFactory.getLogger(MtWebsocketServer.class);


    /**
     * 存储所有websocket链接
     */
    private static Map<String, Session> clients = new ConcurrentHashMap<>();

    public static Map<String, Session> getClients() {
        return clients;
    }

    public static void setClients(Map<String, Session> clients) {
        MtWebsocketServer.clients = clients;
    }

    /**
     * 客户端连接
     *
     * @param session
     * @param deviceCode 设备的唯一标志码
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "deviceCode") String deviceCode) {
        logger.info("有新的客户端连接了: {}", deviceCode);
        clients.put(deviceCode, session);
    }

    /**
     * 发生错误
     *
     * @param throwable e
     */
    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    /**
     * 客户端断开
     *
     * @param deviceCode
     */
    @OnClose
    public void onClose(@PathParam(value = "deviceCode") String deviceCode) {
        logger.info("有用户断开连接了,id为: {}", deviceCode);
        clients.remove(deviceCode);
    }

    /**
     * 收到客户端消息
     *
     * @param message
     */
    @OnMessage
    public void onMessage(String message) throws SQLException,IOException {
        logger.info("服务端收到客户端发来的消息: {}", message);
        this.sendAll("来自服务器的消息:"+message);
        while(true){
            this.sendAll("服务器主动推送消息:hello");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 群发消息
     * @param message 消息内容
     */
    private void sendAll(String message) throws IOException {
        for (Map.Entry<String, Session> sessionEntry : clients.entrySet()) {
            sessionEntry.getValue().getBasicRemote().sendText(message);
        }
    }
}

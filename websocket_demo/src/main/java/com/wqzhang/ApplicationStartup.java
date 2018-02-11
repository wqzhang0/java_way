package com.wqzhang;


import com.wqzhang.websocketInstantMsg.ChatServer;
import org.java_websocket.WebSocketImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;


/**
 * @Description 启动监听
 */
@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Value("${websocket_model}")
    private int websocketModel;
    @Value("${server.ssl.key-store}")
    private String keyStore;
    @Value("${server.ssl.key-store-password}")
    private String storePassword;
    @Value("${server.ssl.key-password}")
    private String password;
    @Value("${WEBSOCKET}")
    private String WEBSOCKET;// 读取WEBSOCKET配置,获取端口配置

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.startWebsocketInstantMsg();
    }


    /**
     * 启动即时聊天服务
     */
    public void startWebsocketInstantMsg() {
        WebSocketImpl.DEBUG = false;
        ChatServer s;
        try {
            if (null != WEBSOCKET && !"".equals(WEBSOCKET)) {
                String[] config = WEBSOCKET.split(":");
                if (websocketModel == 1) {
                    s = new ChatServer(Integer.parseInt(config[1]), keyStore, storePassword, password);

                } else {
                    s = new ChatServer(Integer.parseInt(config[1]));
                }
                s.start();
                System.out.println("websocket服务器启动,端口" + s.getPort());
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


}

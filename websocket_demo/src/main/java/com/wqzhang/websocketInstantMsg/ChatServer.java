package com.wqzhang.websocketInstantMsg;

import com.wqzhang.SpringContextHolder;
import com.wqzhang.activemq.MessageBean;
import com.wqzhang.activemq.Producer;
import net.sf.json.JSONObject;
import org.java_websocket.WebSocket;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.DefaultSSLWebSocketServerFactory;
import org.java_websocket.server.WebSocketServer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


/**
 * @author wqzhang
 * @version 1.0.0
 * @ClassName ChatServer
 * @Description 即时通讯启动类
 * @Date 2018年2月7日
 */
public class ChatServer extends WebSocketServer {

    /**
     * 创建SSL认证的WebSocket
     *
     * @param port
     * @param keyStore
     * @param storePassword
     * @param password
     * @throws UnknownHostException
     */
    public ChatServer(int port, String keyStore, String storePassword,
                      String password) throws UnknownHostException {
        super(new InetSocketAddress(port));
        try {
            TrustManager[] trustAllCerts = new TrustManager[]
                    {new X509TrustManager() {
                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]
                                    {};
                        }

                        @Override
                        public void checkClientTrusted(X509Certificate[] chain,
                                                       String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain,
                                                       String authType) throws CertificateException {
                        }
                    }};

            char keyStorePass[] = storePassword.toCharArray(); // 证书密码
            char keyPassword[] = password.toCharArray(); // 证书别称所使用的主要密码
            KeyStore ks = KeyStore.getInstance("JKS"); // 创建JKS密钥库

            org.springframework.core.io.Resource fileRource = new PathMatchingResourcePatternResolver()
                    .getResource(keyStore);
            ks.load(fileRource.getInputStream(), keyStorePass);

            // 创建管理JKS密钥库的X.509密钥管理器
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");

            kmf.init(ks, keyPassword);

            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(kmf.getKeyManagers(), trustAllCerts,
                    new java.security.SecureRandom());
            WebSocketServerFactory sslSocketFactory = new DefaultSSLWebSocketServerFactory(
                    sc);
            setWebSocketFactory(sslSocketFactory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ChatServer(InetSocketAddress address) {
        super(address);
    }

    /**
     * 创建无SSL认证的WebSocket
     *
     * @param port
     * @throws UnknownHostException
     */
    public ChatServer(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
    }


    /**
     * 触发连接事件
     */
    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
    }

    /**
     * 触发关闭事件
     */
    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        userLeave(conn);
    }

    /**
     * 客户端发送消息到服务器时触发事件
     */
    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("收到消息啦");
        MessageBean messageBean = new MessageBean();
        messageBean.setType(MessageBean.Type.MEG);
        messageBean.setMsg(message);
        // 发送到消息队列中,
        message = message.toString();
        Producer p = SpringContextHolder.getBean("messageProducer");

        if (null != message && message.startsWith("connectionsSuccess")) {
            // 发送消息
            this.userjoin(message.replaceFirst("connectionsSuccess", ""), conn);
            // 同步登陆信息
            messageBean.setMsg(message.split("connectionsSuccess")[1] + "|"
                    + conn.hashCode());
            messageBean.setType(MessageBean.Type.UP_LINE);
        }
        if (null != message && message.startsWith("LeaveConnections")) {
            this.userLeave(conn);
            messageBean.setMsg(message.split("connectionsSuccess")[1] + "|"
                    + conn.hashCode());
            messageBean.setType(MessageBean.Type.OUT_LINE);
        }
        if (null != message && message.contains("Sender")) {
        }
        p.send(messageBean);
    }

    public void onFragment(WebSocket conn, Framedata fragment) {
    }

    /**
     * 触发异常事件
     */
    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
        if (conn != null) {
        }
    }

    /**
     * 用户下线处理
     *
     * @param conn
     */
    public void userLeave(WebSocket conn) {
        String user = ChatServerPool.getUserByKey(conn);
        boolean b = ChatServerPool.removeUser(conn); // 在连接池中移除连接
        if (b) {
            MessageBean messageBean = new MessageBean();
            messageBean.setMsg(user + "|" + conn.hashCode());
            messageBean.setType(MessageBean.Type.OUT_LINE);
            Producer p = SpringContextHolder.getBean("messageProducer");
            p.send(messageBean);
        }
    }

    /**
     * 用户加入处理
     *
     * @param user
     */
    public void userjoin(String user, WebSocket conn) {
        JSONObject result = new JSONObject();
        result = new JSONObject();
        result.element("type", "get_online_user");
        ChatServerPool.addUser(user, conn); // 向连接池添加当前的连接对象
        result.element("list", ChatServerPool.getOnlineUsers(user));
        ChatServerPool.sendMessageToUser(conn, result.toString()); // 向当前连接发送当前在线用户的列表
    }
}

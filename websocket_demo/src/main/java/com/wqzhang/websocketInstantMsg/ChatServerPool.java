package com.wqzhang.websocketInstantMsg;

import org.java_websocket.WebSocket;

import java.util.*;

/**
 * @author wqzhang
 * @version 1.0.0
 * @ClassName ChatServerPool
 * @Description 即时通讯消息通讯池 及 在线用户保存
 * @Date 2018年2月7日
 */
public class ChatServerPool {

    //用来维持本服务器所连接的用户信息
    private static final Map<WebSocket, String> userconnections = new HashMap<WebSocket, String>();

    //用来维持集群内每一个用户连接信息(userName+websocket.hasCode())
    private static final Set<String> onlineUsers = new HashSet<>();

    /**
     * 获取用户名
     *
     * @param conn
     */
    public static String getUserByKey(WebSocket conn) {
        return userconnections.get(conn);
    }

    /**
     * 获取WebSocket
     *
     * @param user
     */
    public static WebSocket getWebSocketByUser(String user) {
        Set<WebSocket> keySet = userconnections.keySet();
        synchronized (keySet) {
            for (WebSocket conn : keySet) {
                String cuser = userconnections.get(conn);
                if (cuser.equals(user)) {
                    return conn;
                }
            }
        }
        return null;
    }

    /**
     * 获取WebSocket
     *
     * @param user
     */
    public static List<WebSocket> getWebSocketsByUser(String user) {
        List<WebSocket> conns = new ArrayList<>();
        Set<WebSocket> keySet = userconnections.keySet();
        synchronized (keySet) {
            for (WebSocket conn : keySet) {
                String cuser = userconnections.get(conn);
                if (cuser.equals(user)) {
                    conns.add(conn);
                }
            }
        }
        return conns;
    }


    /**
     * 向连接池中添加连接
     *
     * @param user
     * @param conn
     */
    public static void addUser(String user, WebSocket conn) {
        userconnections.put(conn, user); // 添加连接
    }

    /**
     * 获取所有的在线用户
     *
     * @return
     */
    public static Collection<String> getOnlineUser() {
        List<String> setUsers = new ArrayList<String>();
        Collection<String> setUser = userconnections.values();
        for (String u : setUser) {
            setUsers.add("<a onclick=\"toUserMsg('" + u + "');\">" + u + "</a>");
        }
        return setUsers;
    }

    /**
     * 移除连接池中的连接
     *
     * @param conn
     * @return
     */
    public static boolean removeUser(WebSocket conn) {
        if (userconnections.containsKey(conn)) {
            userconnections.remove(conn); // 移除连接
            return true;
        } else {
            return false;
        }
    }


    /**
     * 向特定的用户发送数据
     *
     * @param conn
     * @param message
     */
    public static void sendMessageToUser(WebSocket conn, String message) {
        if (null != conn && null != userconnections.get(conn)) {
            conn.send(message);
        }
    }


    /**
     * 向特定的用户所有登陆账号发送数据
     *
     * @param conns
     * @param message
     */
    public static void sendMessageToUsers(List<WebSocket> conns, String message) {
        for (WebSocket conn : conns) {
            if (null != conn && null != userconnections.get(conn)) {
                conn.send(message);
            }
        }
    }

    /**
     * 向所有的用户发送消息
     *
     * @param message
     */
    public static void sendMessage(String message) {
        Set<WebSocket> keySet = userconnections.keySet();
        synchronized (keySet) {
            for (WebSocket conn : keySet) {
                String user = userconnections.get(conn);
                if (user != null) {
                    conn.send(message);
                }
            }
        }
    }

    /**
     * 向所有的用户发送消息
     *
     * @param message
     */
    public static void sendMessage(String message, String ExqUser) {
        Set<WebSocket> keySet = userconnections.keySet();
        synchronized (keySet) {
            for (WebSocket conn : keySet) {
                String user = userconnections.get(conn);
                if (user != null && !user.equals(ExqUser)) {
                    conn.send(message);
                }
            }
        }
    }

    public static Set<String> getOnlineusers() {
        return onlineUsers;
    }

    /**
     * 获取所有的在线用户
     *
     * @return
     */
    public static Collection<String> getOnlineUsers(String currentUser) {
        Set<String> setUser = new HashSet<>();
        List<String> users = new ArrayList<String>();
        Iterator<String> it = onlineUsers.iterator();
        users.add("<a onclick=\"toUserMsg('" + currentUser + "');\">"
                + currentUser + "</a>");
        setUser.add(currentUser);
        while (it.hasNext()) {
            String onlineUserName = it.next().split("\\|")[0];
            if (!setUser.contains(onlineUserName)) {
                users.add("<a onclick=\"toUserMsg('" + onlineUserName
                        + "');\">" + onlineUserName + "</a>");
                setUser.add(onlineUserName);
            }
        }
        return users;
    }

    /**
     * 获取所有的在线用户
     *
     * @return
     */
    public static boolean chekUserOnLine(String userName) {
        Iterator<String> it = onlineUsers.iterator();
        userName = userName.split("\\|")[0];
        while (it.hasNext()) {
            String onlineUserName = it.next().split("\\|")[0];
            if (userName.equals(onlineUserName)) {
                return true;
            }
        }
        return false;
    }

}

package com.wqzhang.activemq;

import com.wqzhang.SpringContextHolder;
import com.wqzhang.websocketInstantMsg.ChatServerPool;
import net.sf.json.JSONObject;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * @author wqzhang
 * @version 1.0.0
 * @ClassName Consumer
 * @Description 即时消息消费者
 * @Date 2018年2月7日
 */
@Component
public class Consumer {


    @JmsListener(destination = "biz.queue", containerFactory = "jmsListenerContainerQueue")
    public void receiveQueue(String text) {
        System.out.println("Consumer queue=" + text);
    }

    @JmsListener(destination = "biz.topic", containerFactory = "jmsListenerContainerTopic")
    public void receiveTopic(MessageBean messageBean) {
        System.out.println("Consumer topic=" + messageBean.toString());
        String message = messageBean.getMsg();
        MessageBean.Type messageType = messageBean.getType();
        if (messageType == MessageBean.Type.MEG) {
            if (null != message && message.contains("Sender")) {
                JSONObject messageObj = JSONObject.fromObject(message);
                String fromUser = (String) messageObj.get("from");
                String toUser = message.substring(message.indexOf("Sender") + 6, message.indexOf("Recipient"));
                String toFromMessage = message.substring(0, message.indexOf("Sender")) + "[私信:发给 " + toUser + "] " + message.substring(message.indexOf("Recipient") + 9, message.length());

                ChatServerPool.sendMessageToUsers(
                        ChatServerPool.getWebSocketsByUser(fromUser), toFromMessage);// 向所有发送者(同一个账号多个登陆地址 同步回话)发送 ]发送消息]

                String toTargetMessage = message.substring(0, message.indexOf("Sender")) + "[来自" + fromUser + "的私信]  " + message.substring(message.indexOf("Recipient") + 9, message.length());
                ChatServerPool.sendMessageToUsers(
                        ChatServerPool.getWebSocketsByUser(toUser), toTargetMessage);// 向所有接受者(接受者有多个登陆地址)发送消息
            } else {
                ChatServerPool.sendMessage(message.toString());// 向所有在线用户发送消息
            }
        } else if (messageType == MessageBean.Type.OUT_LINE) {
            String userName = message;
            ChatServerPool.getOnlineusers().remove(userName);
            //先查看是否有相同账号登陆,如果没有则发送给消息队列,否则不发送上线信息
            if (!ChatServerPool.chekUserOnLine(userName)) {
                Producer p = SpringContextHolder.getBean("messageProducer");

                MessageBean messageBean2 = new MessageBean();
                messageBean2.setType(MessageBean.Type.MEG);
                JSONObject result = new JSONObject();
                result.element("type", "user_leave");
                result.element("user", "<a onclick=\"toUserMsg('" + userName.split("\\|")[0] + "');\">" + userName.split("\\|")[0] + "</a>");
                messageBean2.setMsg(result.toString());
                p.send(messageBean2);

                String joinMsg = "{\"from\":\"[系统]\",\"content\":\"" + userName.split("\\|")[0] + "下线了\",\"timestamp\":" + new Date().getTime() + ",\"type\":\"message\"}";
                messageBean2.setMsg(result.toString());
                p.send(messageBean2);
                ChatServerPool.sendMessage(joinMsg);
            }

        } else if (messageType == MessageBean.Type.UP_LINE) {
            String userName = message.split("\\|")[0];
            //先查看是否有相同账号登陆,如果没有则发送给消息队列,否则不发送上线信息
            if (!ChatServerPool.chekUserOnLine(userName)) {
                MessageBean messageBean2 = new MessageBean();
                messageBean2.setType(MessageBean.Type.ON_LINE_TIP);
                JSONObject result = new JSONObject();
                result.element("type", "user_join");
                result.element("user", userName);
                messageBean2.setMsg(result.toString());
//                ChatServerPool.sendMessage(result.toString());                //把当前用户加入到所有在线用户列表中  除了登陆用户本身
                Producer p = SpringContextHolder.getBean("messageProducer");
                p.send(messageBean2);


                messageBean2.setType(MessageBean.Type.MEG);
                String joinMsg = "{\"from\":\"[系统]\",\"content\":\"" + userName + "上线了\",\"timestamp\":" + new Date().getTime() + ",\"type\":\"message\"}";
                messageBean2.setMsg(joinMsg);
                p.send(messageBean2);
//                reSend(messageBean);
            }
            ChatServerPool.getOnlineusers().add(message);
        } else if (messageType == MessageBean.Type.ON_LINE_TIP) {
            //除去 发送者本身
            JSONObject recevierObj = JSONObject.fromObject(message);
            String userName = (String) recevierObj.get("user");

            JSONObject result = new JSONObject();
            result.element("type", "user_join");
            result.element("user", "<a onclick=\"toUserMsg('" + userName + "');\">" + userName + "</a>");

            ChatServerPool.sendMessage(result.toString(), userName);// 向所有在线用户发送消息

        }
    }

}

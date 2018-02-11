package com.wqzhang.activemq;

import java.io.Serializable;

/**
 * @author wqzhang
 * @version 1.0.0
 * @ClassName MessageBean
 * @Description activeMQ 消息传递Bean
 * @Date 2018年2月7日
 */
public class MessageBean implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -1129721491991090860L;

    private Type type;

    private String msg;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "MessageBean [type=" + type + ", msg=" + msg + "]";
    }

    public static enum Type {
        UP_LINE, OUT_LINE, MEG, ON_LINE_TIP
    }

}

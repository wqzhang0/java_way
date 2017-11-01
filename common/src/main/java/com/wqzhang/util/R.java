package com.wqzhang.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wqzhang on 2017/10/27.
 * @author wqzhang
 */
public class R extends HashMap<String, Object> {
    private static final Long serialVersionUID = 1L;
    //返回值键
    private final static String CODE = "code";
    private final static String MSG = "msg";
    //返回结果值
    private final static int SUCCESS_CODE = 0;
    private final static int ERROR_CODE = 500;

    private R() {
        put(CODE, SUCCESS_CODE);
        put(MSG, "操作成功");
    }

    public static R error() {
        return error("操作失败");
    }

    public static R error(String msg) {
        return error(ERROR_CODE, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put(CODE, code);
        r.put(MSG, msg);
        return r;
    }

    /**
     * 返回值map 错误
     *
     * @param map
     * @return
     */
    public static R error(Map<String, Object> map) {
        R r = new R();
        r.put(CODE, ERROR_CODE);
        r.putAll(map);
        return r;
    }

    /**
     * 返回成功方法
     *
     * @param msg
     * @return
     */
    public static R success(String msg) {
        R r = new R();
        r.put(MSG, msg);
        return r;
    }

    /**
     * 返回值map
     *
     * @param map
     * @return
     */
    public static R success(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R success() {
        return new R();
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}

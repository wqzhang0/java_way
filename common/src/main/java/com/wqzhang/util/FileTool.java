package com.wqzhang.util;

import com.sun.corba.se.spi.orbutil.fsm.Input;

import java.io.*;

/**
 * Created by wqzhang on 2017/8/24.
 */
public class FileTool {
    /**
     * 输出文件
     *
     * @param content  字符串
     * @param fileName 路径
     * @throws IOException
     */
    public static void outPutFile(String content, String fileName) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName));
        StringBuffer sb = new StringBuffer();
        fileOutputStream.write(content.getBytes());
        fileOutputStream.close();
    }

    /**
     * 输出文件
     *
     * @param content  字符串
     * @param fileName 路径
     * @throws IOException
     */
    public static void outPutFile2(String content, String fileName) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName));
        StringBuffer sb = new StringBuffer();
        fileOutputStream.write(content.getBytes());
        fileOutputStream.close();
    }

    /**
     * 获得文件的数据流
     *
     * @param fileName 路径
     * @throws IOException
     */
    public static InputStream getInputStream(String fileName) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(new File(fileName));
        return fileInputStream;
    }
}

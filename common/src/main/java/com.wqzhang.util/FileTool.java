package com.wqzhang.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by wqzhang on 2017/8/24.
 */
public class FileTool {
    /**
     * ����ļ�
     *
     * @param content  �ַ���
     * @param fileName ·��
     * @throws IOException
     */
    public static void outPutFile(String content, String fileName) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName));
        StringBuffer sb = new StringBuffer();
        fileOutputStream.write(content.getBytes());
        fileOutputStream.close();
    }

    /**
     * ����ļ�
     *
     * @param content  �ַ���
     * @param fileName ·��
     * @throws IOException
     */
    public static void outPutFile2(String content, String fileName) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName));
        StringBuffer sb = new StringBuffer();
        fileOutputStream.write(content.getBytes());
        fileOutputStream.close();
    }
}

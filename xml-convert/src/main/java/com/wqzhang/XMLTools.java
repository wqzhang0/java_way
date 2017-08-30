package com.wqzhang;


import com.wqzhang.tools.DOM4JTools;
import com.wqzhang.tools.JDOMTools;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * Created by wqzhang on 2017/8/24.
 * <p>
 * <p>
 * ������Ĵ��������ʹ�õ���FileWriter��������ļ������������ǲ���
 * <p>
 * ��ȷ�����ļ������ԭ�����ڣ�java����Writer��̳�����������û���ṩ����
 * <p>
 * ��ʽ��������dom4jҲ���޷���������ļ�������ȷ�ĸ�ʽ������ʱ������
 * <p>
 * ����ļ�����ϵͳ��Ĭ�ϱ�����ļ����б��棬�����İ��window��java��Ĭ��
 * <p>
 * �ı���ΪGBK��Ҳ��������Ȼ���Ǳ�ʶ��Ҫ��xml����Ϊutf-8��ʽ��ʵ�����ļ�
 * <p>
 * ����GBK��ʽ������ģ�������Ҳ����Ϊʲô�ܹ�����ʹ��GBK��GB2312��������
 * <p>
 * ��xml�ļ�����ȷ�ı�����������UTF-8��ʽ���ɵ��ļ����ܱ�xml������������
 * <p>
 * ��ԭ��
 */
public class XMLTools {
    public static final int WAY_JDOM = 1;
    public static final int WAY_DOM4J = 2;

    public void create(int type) {

        try {
            if (WAY_JDOM == type) {
                JDOMTools.getInstance().create();
            } else if (WAY_DOM4J == type) {
                DOM4JTools.getInstance().create();
            }

        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void parse(String filePath, int type) {
        try {
            if (WAY_JDOM == type) {
//                JDOMTools.getInstance().par();
            } else if (WAY_DOM4J == type) {
                DOM4JTools.getInstance().parse(filePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

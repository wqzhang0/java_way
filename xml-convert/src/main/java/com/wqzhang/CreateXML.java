package com.wqzhang;


import com.wqzhang.tools.DOM4JTools;
import com.wqzhang.tools.JDOMTools;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * Created by wqzhang on 2017/8/24.
 *
 *
 *������Ĵ��������ʹ�õ���FileWriter��������ļ������������ǲ���
 *
 *��ȷ�����ļ������ԭ�����ڣ�java����Writer��̳�����������û���ṩ����
 *
 *��ʽ��������dom4jҲ���޷���������ļ�������ȷ�ĸ�ʽ������ʱ������
 *
 *����ļ�����ϵͳ��Ĭ�ϱ�����ļ����б��棬�����İ��window��java��Ĭ��
 *
 *�ı���ΪGBK��Ҳ��������Ȼ���Ǳ�ʶ��Ҫ��xml����Ϊutf-8��ʽ��ʵ�����ļ�
 *
 *����GBK��ʽ������ģ�������Ҳ����Ϊʲô�ܹ�����ʹ��GBK��GB2312��������
 *
 *��xml�ļ�����ȷ�ı�����������UTF-8��ʽ���ɵ��ļ����ܱ�xml������������
 *
 *��ԭ��

 */
public class CreateXML {
    public static final int WAY_JDOM = 1;
    public static final int WAY_DOM4J = 2;

    public void create(int type) {

        try {
            if (WAY_JDOM == type) {
                JDOMTools.getInstance().create();
            }else if(WAY_DOM4J == type){
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

}

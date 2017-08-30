package com.wqzhang.tools;

import com.wqzhang.intefter.CreateXML;
import com.wqzhang.intefter.ParseXML;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.List;

/**
 * Created by wqzhang on 2017/8/29.
 */
public class DOM4JTools implements CreateXML, ParseXML {

    private static DOM4JTools dom4JTools;

    public static DOM4JTools getInstance() {
        if (dom4JTools == null) {
            dom4JTools = new DOM4JTools();
        }
        return dom4JTools;
    }

    private void DOM4JTools() {
    }

    public void create() throws Exception {
        Document document = DocumentHelper.createDocument();
//        document.setXMLEncoding("GBK");
        Element root = document.addElement("Root");
        root.addElement("�������ĸ�ʽ�Ƿ�����");
        Element classElement = root.addElement("Class");
        classElement.addAttribute("id", "class_id");
        Element teachersElement = classElement.addElement("teachers");
        teachersElement.addAttribute("id", "teachers");
        Element chineseTeacher = teachersElement.addElement("chineseTeacher");
        chineseTeacher.addAttribute("age", "24");
        chineseTeacher.setText("Mr Zhang");
        Element englishTeacher = teachersElement.addElement("englishTeacher");
        englishTeacher.setText("Mr Liang");
        englishTeacher.addAttribute("age", "21");

//        teachersElement.addEntity("������ʦ", "����ʦ");

//        System.out.print(document.asXML());


//        �����Ҫ�������  ʹ��OutputStreamWriter ����  ��Ȼ����
//        Writer writer = new FileWriter("D:\\java_way\\xml-convert\\res\\DOM4JOut.xml");

        /**
         * ͨ�� org.dom4j.io.OutputFormat ������XML�ĵ������ʽ
         */
        OutputFormat format = OutputFormat.createPrettyPrint(); //����XML�ĵ������ʽ
        format.setEncoding("GBK"); //����XML�ĵ��ı�������
//        format.setSuppressDeclaration(true);
//        format.setIndent(true); //�����Ƿ�����
//        format.setIndent(" "); //�Կո�ʽʵ������
//        format.setNewlines(true); //�����Ƿ���

        Writer writer = new OutputStreamWriter(new FileOutputStream(new File("D:\\java_way\\xml-convert\\res\\DOM4JOut.xml")));
        //ʹ��dom4j �Լ��ṩ�����������
        XMLWriter xmlWriter = new XMLWriter(writer, format);
        xmlWriter.write(document);
        xmlWriter.flush();
        xmlWriter.close();
    }

    public void parse(String filePath) {
        //��ȡ�ļ���
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new File(filePath));
            Element rootElement = document.getRootElement();
            List<Element> elementList = rootElement.elements();
            for (Element element : elementList) {
                System.out.println(element.getQName().getNamespace().getPrefix() + "| " + element.getNamespaceURI());
                System.out.println(element.getName() + "  " + element.getText());

            }
//            element.getName()
        } catch (DocumentException e) {
            e.printStackTrace();
        }


    }
}

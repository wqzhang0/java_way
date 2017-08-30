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
        root.addElement("检验中文格式是否乱码");
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

//        teachersElement.addEntity("语文老师", "张老师");

//        System.out.print(document.asXML());


//        如果需要输出中文  使用OutputStreamWriter 方法  不然乱码
//        Writer writer = new FileWriter("D:\\java_way\\xml-convert\\res\\DOM4JOut.xml");

        /**
         * 通过 org.dom4j.io.OutputFormat 来设置XML文档输出格式
         */
        OutputFormat format = OutputFormat.createPrettyPrint(); //设置XML文档输出格式
        format.setEncoding("GBK"); //设置XML文档的编码类型
//        format.setSuppressDeclaration(true);
//        format.setIndent(true); //设置是否缩进
//        format.setIndent(" "); //以空格方式实现缩进
//        format.setNewlines(true); //设置是否换行

        Writer writer = new OutputStreamWriter(new FileOutputStream(new File("D:\\java_way\\xml-convert\\res\\DOM4JOut.xml")));
        //使用dom4j 自己提供过的输出方法
        XMLWriter xmlWriter = new XMLWriter(writer, format);
        xmlWriter.write(document);
        xmlWriter.flush();
        xmlWriter.close();
    }

    public void parse(String filePath) {
        //获取文件流
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

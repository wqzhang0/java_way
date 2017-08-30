package com.wqzhang.tools;

import com.wqzhang.util.FileTool;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by wqzhang on 2017/8/29.
 */
public class JDOMTools implements CreateXML {

    private static JDOMTools jdomTools;

    public static JDOMTools getInstance() {
        if (jdomTools == null) {
            jdomTools = new JDOMTools();
        }
        return jdomTools;
    }

    private JDOMTools() {

    }

    public void create() throws Exception {
        DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docbuilder = dbfactory.newDocumentBuilder();
        //创建 Documet
        Document document = docbuilder.newDocument();
        //增加第一个节点
        Element root = document.createElement("Students");
        document.appendChild(root);
        //创建子节点
        Element studentA = document.createElement("studentA");
        Element studentB = document.createElement("studentB");
        Element studentC = document.createElement("studentC");
        Element studentD = document.createElement("studentD");
        Element studentE = document.createElement("studentE");

        studentA.setAttribute("Name", "NAME_A");
        studentA.setTextContent("我是A  我很自信");
        studentB.setAttribute("Name", "NAME_B");
        studentB.setNodeValue("NodeValue");
        studentB.appendChild(document.createTextNode("textNode"));
        studentC.setAttribute("Name", "NAME_C");
        studentD.setAttribute("Name", "NAME_D");
        studentE.setAttribute("Name", "NAME_E");

        root.appendChild(studentA);
        root.appendChild(studentB);
        root.appendChild(studentC);
        root.appendChild(studentD);
        root.appendChild(studentE);

        StringWriter outWriter = new StringWriter();
        StreamResult streamResult = new StreamResult(outWriter);

        TransformerFactory tfactory = TransformerFactory.newInstance();
        Transformer transformer = tfactory.newTransformer();
        //设置换行
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        //设置格式 4个空格（猜测）
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        // 因为个人项目使用GBK编码，这里使用GBK格式输出  否则乱码
        transformer.setOutputProperty("encoding", "GBK");

        transformer.transform(new DOMSource(root), streamResult);
        String createStr = outWriter.getBuffer().toString();
        System.out.print(createStr);
        FileTool.outPutFile(createStr, "D:\\java_way\\xml-convert\\res\\JDOMOut.xml");
    }
}

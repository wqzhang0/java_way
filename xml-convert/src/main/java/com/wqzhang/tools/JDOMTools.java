package com.wqzhang.tools;

import com.wqzhang.intefter.CreateXml;
import com.wqzhang.util.FileTool;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

/**
 * Created by wqzhang on 2017/8/29.
 */
public class JDOMTools implements CreateXml {

    private static JDOMTools jdomTools;

    public static JDOMTools getInstance() {
        if (jdomTools == null) {
            jdomTools = new JDOMTools();
        }
        return jdomTools;
    }

    private JDOMTools() {

    }

    @Override
    public void create() throws Exception {
        DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docbuilder = dbfactory.newDocumentBuilder();
        //���� Documet
        Document document = docbuilder.newDocument();
        //���ӵ�һ���ڵ�
        Element root = document.createElement("Students");
        document.appendChild(root);
        //�����ӽڵ�
        Element studentA = document.createElement("studentA");
        Element studentB = document.createElement("studentB");
        Element studentC = document.createElement("studentC");
        Element studentD = document.createElement("studentD");
        Element studentE = document.createElement("studentE");

        studentA.setAttribute("Name", "NAME_A");
        studentA.setTextContent("����A  �Һ�����");
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
        //���û���
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        //���ø�ʽ 4���ո񣨲²⣩
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        // ��Ϊ������Ŀʹ��GBK���룬����ʹ��GBK��ʽ���  ��������
        transformer.setOutputProperty("encoding", "GBK");

        transformer.transform(new DOMSource(root), streamResult);
        String createStr = outWriter.getBuffer().toString();
        System.out.print(createStr);
        FileTool.outPutFile(createStr, "D:\\java_way\\xml-convert\\res\\JDOMOut.xml");
    }
}

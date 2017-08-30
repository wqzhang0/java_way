package com.wqzhang;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * Created by wqzhang on 2017/8/24.
 */
public class Main {
    public static void main(String[] args0) throws IOException, TransformerException, ParserConfigurationException {
        //DOM、SAX、JDOM和DOM4J

        //生成XML 文件
        XMLTools xmlTools = new XMLTools();
//        xmlTools.create(CreateXML.WAY_JDOM);
//        xmlTools.create(XMLTools.WAY_DOM4J);
        xmlTools.parse("D:\\java_way\\xml-convert\\res\\testParse.xml",XMLTools.WAY_DOM4J);
    }
}

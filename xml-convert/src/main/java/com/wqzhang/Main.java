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
        CreateXML createXML = new CreateXML();
        createXML.create(CreateXML.WAY_JDOM);
    }
}

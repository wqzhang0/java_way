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
 * 在上面的代码中输出使用的是FileWriter对象进行文件的输出。这就是不能
 * <p>
 * 正确进行文件编码的原因所在，java中由Writer类继承下来的子类没有提供编码
 * <p>
 * 格式处理，所以dom4j也就无法对输出的文件进行正确的格式处理。这时候所保
 * <p>
 * 存的文件会以系统的默认编码对文件进行保存，在中文版的window下java的默认
 * <p>
 * 的编码为GBK，也就是所虽然我们标识了要将xml保存为utf-8格式但实际上文件
 * <p>
 * 是以GBK格式来保存的，所以这也就是为什么能够我们使用GBK、GB2312编码来生
 * <p>
 * 成xml文件能正确的被解析，而以UTF-8格式生成的文件不能被xml解析器所解析
 * <p>
 * 的原因。
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

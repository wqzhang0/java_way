import com.wqzhang.ReadExcelUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author wqzhang
 * @data 2017/11/1.
 */
public class main {
    public static void main(String[] args) {
        ReadExcelUtil readExcelUtil = new ReadExcelUtil();
        try {
            List<Map<String,String>> datas = readExcelUtil.readExcel("D:\\JAVA\\java_way\\java_way\\excel_tools\\src\\main\\resources\\缴费列表.xls");
            System.out.print(datas.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

import com.wqzhang.ExportDataModel_bl;
import com.wqzhang.ExportExcelUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wqzhang
 * @data 2017/11/1.
 */
public class ExportExcelUtilTest {
    public static void main(String[] args) {
        ExportExcelUtil exportExcelUtil = new ExportExcelUtil();
        List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
        data = createData(data);

        String fileName = "test";
        ArrayList<ExportExcelUtil.ExportDataModel> dataModelArray = new ArrayList<ExportExcelUtil.ExportDataModel>();

        dataModelArray.add(ExportExcelUtil.builder("姓名","name"));
        dataModelArray.add(ExportExcelUtil.builder("年龄","age",ExportExcelUtil.Type.INT));
        dataModelArray.add(ExportExcelUtil.builder("状态","status","0:有效|1:无效"));


        exportExcelUtil.export(fileName,dataModelArray,data);


    }

    private static List<Map<String,Object>> createData(List<Map<String,Object>> data){
        Map<String,Object> map1 = new HashMap<String, Object>();
        Map<String,Object> map2 = new HashMap<String, Object>();
        Map<String,Object> map3 = new HashMap<String, Object>();
        map1.put("name","张文强");
        map2.put("age",18);
        map3.put("status","1");
        data.add(map1);
        data.add(map2);
        data.add(map3);
        return data;

    }
}

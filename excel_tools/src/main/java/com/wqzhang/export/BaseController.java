package com.wqzhang.export;

import com.wqzhang.export.util.ExportDataModel;
import com.wqzhang.export.util.UniversalExcelView;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wqzhang
 * @version 1.0.0
 * @ClassName BaseClass
 * @Description 导出通用类,应该为BaseController 所有的 conroller继承 BaseController  调用super.export(exportDataModel);
 * @Date 2017/12/26 14:03
 */
public class BaseController {

    protected ModelAndView export(ExportDataModel exportDataModel) {
        ModelAndView mv = new ModelAndView();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        //文件名
        dataMap.put("fileName", exportDataModel.getFileName());
        //需要写入的数据
        dataMap.put("datas", exportDataModel.getDatas());
        //每列对应的sql查询关键字
        dataMap.put("sqlKeys", exportDataModel.getKeys());
        //每列对应的标题
        dataMap.put("titles", exportDataModel.getTitles());
        //每列对应的类型
        dataMap.put("types", exportDataModel.getTypes());
        //需要进行数值转换时的规则
        dataMap.put("specialTypeMap", exportDataModel.getSpecialTypeMap());
        //合计数据
        dataMap.put("totalMap", exportDataModel.getTotalMap());
        //列数
        dataMap.put("columsLength", exportDataModel.getColumsLength());
        UniversalExcelView erv = new UniversalExcelView();
        mv = new ModelAndView(erv, dataMap);
        return mv;
    }
}

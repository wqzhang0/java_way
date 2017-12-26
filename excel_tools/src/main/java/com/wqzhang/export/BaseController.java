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
 * @Description ����ͨ����,Ӧ��ΪBaseController ���е� conroller�̳� BaseController  ����super.export(exportDataModel);
 * @Date 2017/12/26 14:03
 */
public class BaseController {

    protected ModelAndView export(ExportDataModel exportDataModel) {
        ModelAndView mv = new ModelAndView();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        //�ļ���
        dataMap.put("fileName", exportDataModel.getFileName());
        //��Ҫд�������
        dataMap.put("datas", exportDataModel.getDatas());
        //ÿ�ж�Ӧ��sql��ѯ�ؼ���
        dataMap.put("sqlKeys", exportDataModel.getKeys());
        //ÿ�ж�Ӧ�ı���
        dataMap.put("titles", exportDataModel.getTitles());
        //ÿ�ж�Ӧ������
        dataMap.put("types", exportDataModel.getTypes());
        //��Ҫ������ֵת��ʱ�Ĺ���
        dataMap.put("specialTypeMap", exportDataModel.getSpecialTypeMap());
        //�ϼ�����
        dataMap.put("totalMap", exportDataModel.getTotalMap());
        //����
        dataMap.put("columsLength", exportDataModel.getColumsLength());
        UniversalExcelView erv = new UniversalExcelView();
        mv = new ModelAndView(erv, dataMap);
        return mv;
    }
}

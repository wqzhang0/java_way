package com.wqzhang.export;

import com.wqzhang.export.util.Const;
import com.wqzhang.export.util.ExportDataModel;
import com.wqzhang.model.PageData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wqzhang
 * @version 1.0.0
 * @ClassName ExportControllerDemo
 * @Description ${todo}
 * @Date 2017/12/26 14:12
 */
public class ExportControllerDemo extends BaseController {
    /**
     * @Description TODO(������excel)
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/exportExcel")
    public ModelAndView exportExcel() throws Exception
    {
//        PageData pd = this.getPageData();
//        List<PageData> varOList = vipClassService.listAll(pd);
        List<PageData> varOList = new ArrayList<PageData>();

        ExportDataModel exportDataModel = new ExportDataModel();
        exportDataModel.addColumn("��������", "CITY_NAME");
        exportDataModel.addColumn("�༶����", "NAME");
        exportDataModel.addColumn("�����ܼ�", "EXPENSE",ExportDataModel.Type.DOUBLE);
        exportDataModel.addColumn("Э��", "TITLE");
        exportDataModel.addColumn("�༶����", "TYPE", Const.SPECIAL_VIPCLASS_TYPE);
        exportDataModel.addColumn("�Ƿ������ѧ", "IS_FREE",Const.SPECIAL_VIPCLASS_IS_FREE);
        exportDataModel.addColumn("״̬", "STATUS",Const.SPECIAL_STUDENT_STATUS);

        exportDataModel.addData(varOList);
        exportDataModel.setFileName("���ܰ��б�");
        //�����ܼ���
        exportDataModel.addTotalColumn("EXPENSE","193");
        return super.export(exportDataModel);
    }
}

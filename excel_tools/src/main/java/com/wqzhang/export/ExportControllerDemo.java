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
     * @Description TODO(导出到excel)
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
        exportDataModel.addColumn("城市名称", "CITY_NAME");
        exportDataModel.addColumn("班级名称", "NAME");
        exportDataModel.addColumn("城市总价", "EXPENSE",ExportDataModel.Type.DOUBLE);
        exportDataModel.addColumn("协议", "TITLE");
        exportDataModel.addColumn("班级类型", "TYPE", Const.SPECIAL_VIPCLASS_TYPE);
        exportDataModel.addColumn("是否免费试学", "IS_FREE",Const.SPECIAL_VIPCLASS_IS_FREE);
        exportDataModel.addColumn("状态", "STATUS",Const.SPECIAL_STUDENT_STATUS);

        exportDataModel.addData(varOList);
        exportDataModel.setFileName("智能班列表");
        //设置总计行
        exportDataModel.addTotalColumn("EXPENSE","193");
        return super.export(exportDataModel);
    }
}

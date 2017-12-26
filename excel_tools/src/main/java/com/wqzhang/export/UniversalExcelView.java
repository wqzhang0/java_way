package com.wqzhang.export;

import com.wqzhang.model.PageData;
import com.wqzhang.util.Tools;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author wqzhang
 * @version 1.0.0
 * @ClassName UniversalExcelView
 * @Description 通用导出类
 * @Date 2017年11月28日
 */
@SuppressWarnings("deprecation")
public class UniversalExcelView extends AbstractExcelView {
    /**
     * 使用过的 特殊状态 字符转换 Map 每次根据数值进行转换时,优先从中获取 以免再次根据条件分割规则字符串
     */
    private static Map<String, Map<Object, String>> allSpecialMap = new HashMap<String, Map<Object, String>>();
    private SimpleDateFormat dateFormatYMD = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat dateFormatYMDHM = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm");
    private SimpleDateFormat dateFormatYMDHMSS = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    @SuppressWarnings("unchecked")
    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      HSSFWorkbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        // 这里对model 做解析 提取需要的数据
        /**
         * 文件名
         */
        String fileName = String.valueOf(model.get("fileName"));

        /**
         * sql 关键字
         */
        String[] keys = (String[]) model.get("sqlKeys");

        /**
         * 数据类型
         */
        ExportDataModel.Type[] types = (ExportDataModel.Type[]) model.get("types");

        /**
         * 表第一行字段名称
         */
        String[] titles = (String[]) model.get("titles");

        /**
         * 字段转译规则Map
         */
        Map<String, String> specialTypeMap = (Map<String, String>) model
                .get("specialTypeMap");

        /**
         * 总计Map
         */
        Map<String, Object> totalMap = (Map<String, Object>) model
                .get("totalMap");

        /**
         * 总列数
         */
        int columsLength = Integer.valueOf(model.get("columsLength").toString());

        /**
         * 数据
         */
        List<PageData> datas = (List<PageData>) model.get("datas");

        HSSFSheet sheet;
        HSSFCell cell;
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="
                + new String(fileName.getBytes("gbk"), "iso8859-1") + ".xls");

        sheet = workbook.createSheet("sheet1");

        HSSFCellStyle headerStyle = workbook.createCellStyle(); // 标题样式
        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFFont headerFont = workbook.createFont(); // 标题字体
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerFont.setFontHeightInPoints((short) 11);
        headerStyle.setFont(headerFont);
        short width = 20, height = 25 * 20;
        sheet.setDefaultColumnWidth(width);

        // 设置序号列
        cell = getCell(sheet, 0, 0);
        cell.setCellStyle(headerStyle);
        setText(cell, "序号");
        for (int i = 0; i < columsLength; i++) { // 设置标题
            String title = titles[i];
            cell = getCell(sheet, 0, i + 1);
            cell.setCellStyle(headerStyle);
            setText(cell, title);
        }
        sheet.getRow(0).setHeight(height);
        HSSFCellStyle contentStyle = workbook.createCellStyle(); // 内容样式
        contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 总行数
        int rowTotalNum = datas.size();

        // 根据 行号 获取类型 不同的类型调用不同的存值方法
        // 类型包含 int long double string
        // 默认是String类型
        CellStyle cellStyle = workbook.createCellStyle();

        HSSFDataFormat doubleFormat = workbook.createDataFormat();

        // 填充数据
        for (int rowIndex = 0; rowIndex < rowTotalNum; rowIndex++) {
            PageData tmpData = datas.get(rowIndex);

            // 设置 序号
            cell = getCell(sheet, rowIndex + 1, 0);
            cell.setCellStyle(contentStyle);
            setIntNum(cell, rowIndex + 1 + "");

            for (int columnsIndex = 0; columnsIndex < columsLength; columnsIndex++) {
                cell = getCell(sheet, rowIndex + 1, columnsIndex + 1);
                cell.setCellStyle(contentStyle);

                ExportDataModel.Type type = types[columnsIndex];
                String dataValue = "";
                switch (type) {
                    case STRING:
                        dataValue = tmpData.get(keys[columnsIndex]) != null ? String
                                .valueOf(tmpData.get(keys[columnsIndex])) : "";
                        setText(cell, dataValue);
                        break;
                    case INT:
                        dataValue = tmpData.get(keys[columnsIndex]) != null ? String
                                .valueOf(tmpData.get(keys[columnsIndex])) : "";
                        setIntNum(cell, dataValue);
                        break;
                    case DOUBLE:
                        dataValue = Tools.notEmpty(tmpData.get(keys[columnsIndex])) ? new DecimalFormat(
                                "#.00").format(Double.parseDouble(tmpData.get(
                                keys[columnsIndex]).toString())) : "";

                        cellStyle.setDataFormat(doubleFormat.getFormat("#,#0.00"));
                        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                        cell.setCellStyle(cellStyle);
                        setDoubleNum(cell, dataValue);
                        break;
                    case BIGDECIMAL:
                        break;
                    case SPECIAL:
                        dataValue = tmpData.get(keys[columnsIndex]) != null ? String
                                .valueOf(tmpData.get(keys[columnsIndex])) : "";
                        setText(cell,
                                conversionSpecial(dataValue,
                                        specialTypeMap.get(keys[columnsIndex])));
                        break;
                    case DATE_Y_M_D_H_M:
                        dataValue = tmpData.get(keys[columnsIndex]) != null ? dateFormatYMDHM
                                .format(tmpData.get(keys[columnsIndex])) : "";
                        setText(cell, dataValue);
                        break;
                    case DATE_Y_M_D:
                        dataValue = tmpData.get(keys[columnsIndex]) != null ? dateFormatYMD
                                .format(tmpData.get(keys[columnsIndex])) : "";
                        setText(cell, dataValue);
                        break;
                    case DATE_Y_M_D_H_M_S:
                        dataValue = tmpData.get(keys[columnsIndex]) != null ? dateFormatYMDHMSS
                                .format(tmpData.get(keys[columnsIndex])) : "";
                        setText(cell, dataValue);
                        break;

                }
            }
        }
        // 如果存在总计 则在最后一行加入合计
        if (totalMap.size() > 0) {
            // 设置 合计表头
            cell = getCell(sheet, rowTotalNum + 1, 0);
            cell.setCellStyle(contentStyle);
            setText(cell, "合计");
            for (int i = 0; i < columsLength; i++) {
                String theKey = keys[i];
                if (totalMap.containsKey(theKey)) {
                    cell = getCell(sheet, rowTotalNum + 1, i + 1);
                    cell.setCellStyle(contentStyle);
                    setText(cell, totalMap.get(theKey).toString());
                }
            }
        }
    }

    /**
     * 给cell写入double类型的数值
     *
     * @param cell 单元格对象
     * @param text 文本数据
     */
    private void setDoubleNum(HSSFCell cell, String text) {
        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
        if (!Tools.isEmpty(text)) {
            Double doubleValue = Double.valueOf(text);
            cell.setCellValue(doubleValue);
        }
    }

    /**
     * 给cell写入Int类型的数值
     *
     * @param cell 单元格对象
     * @param text 文本数据
     */
    private void setIntNum(HSSFCell cell, String text) {
        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
        if (!Tools.isEmpty(text)) {
            Integer intValue = Integer.valueOf(text);
            cell.setCellValue(intValue);
        }
    }

    /**
     * 根据类型去转换值
     *
     * @param key
     * @param way
     * @return
     */
    private String conversionSpecial(Object key, String way) {
        if (null == key || "".equals((CharSequence) key)) {
            return "";
        }
        Map<Object, String> wayMap = null;
        if (allSpecialMap.containsKey(way)) {
            wayMap = allSpecialMap.get(way);
        } else {
            wayMap = new HashMap<Object, String>();
            String[] specials = way.split(",");
            for (String tmpSpecial : specials) {
                String[] theSpecial = tmpSpecial.split(":");
                wayMap.put(theSpecial[0], theSpecial[1]);
            }
            allSpecialMap.put(way, wayMap);
        }
        return wayMap.get(key);
    }
}

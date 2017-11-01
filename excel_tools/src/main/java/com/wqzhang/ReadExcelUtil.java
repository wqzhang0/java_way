package com.wqzhang;

import com.wqzhang.util.FileTool;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wqzhang
 * @data 2017/11/1.
 */
public class ReadExcelUtil {
    private static final String EXCEL_TYPE_2003 = "xls";
    private static final String EXCEL_TYPE_2007 = "xlsx";


    public List<Map<String, String>> readExcel(String filePath) throws IOException {
//        String[] splits = filePath.split(".");
//        String Type = splits[splits.length - 1];


//        Workbook workbook = getWorkbook(FileTool.getInputStream(filePath), Type);

        Workbook workbook = getWorkbook(FileTool.getInputStream(filePath));

        Sheet sheet = workbook.getSheetAt(0);

        int rowNums = sheet.getLastRowNum();
        //这里最好用表头的cell num
        //因为会存在空的cell 导致缺少数据
        int cellNums = sheet.getRow(0).getLastCellNum();

        List<Map<String, String>> rowDatas = new ArrayList<Map<String, String>>(rowNums);

        for (int rowIndex = 0; rowIndex < rowNums; rowIndex++) {
            Row row = sheet.getRow(rowIndex);

            Map<String, String> cellDataMap = new HashMap<String, String>(cellNums);
            for (int cellIndex = 0; cellIndex < cellNums; cellIndex++) {
                Cell cell = row.getCell(cellIndex);
                String cellValue = "";
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        cellValue = cell.getStringCellValue();
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        cellValue = cell.getBooleanCellValue() + "";
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        //数字类型又包含 时间格式 和数据格式
                        //和单元格的格式有关。有些 显示是时间样式的。可能是String 类型。
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = cell.getDateCellValue();
                            if(date!=null){
                                cellValue = simpleDateFormat.format(date);
                            }
                        } else {
                            cellValue = cell.getNumericCellValue() + "";
                        }
                        break;
                    case Cell.CELL_TYPE_ERROR:
                        cellValue = cell.getErrorCellValue() + "";
                        break;
                    default:
                        break;
                }
                cellDataMap.put("var" + cellIndex, cellValue);
            }
            rowDatas.add(cellDataMap);
        }
        return rowDatas;
    }

    public Workbook getWorkbook(InputStream inputStream, String Type) throws IOException {
        Workbook workbook = null;
        if (Type == null || Type.equals("")) {
            return null;
        }
        if (EXCEL_TYPE_2003.equals(Type)) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (EXCEL_TYPE_2007.equals(Type)) {
            workbook = new XSSFWorkbook(inputStream);
        }
        return workbook;
    }

    public Workbook getWorkbook(InputStream inputStream) {
        Workbook workbook = null;
        try {
            workbook = new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            try {
                workbook = new XSSFWorkbook(inputStream);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return workbook;
    }

}

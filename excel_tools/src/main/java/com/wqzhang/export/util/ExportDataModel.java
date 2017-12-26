package com.wqzhang.export.util;

import com.wqzhang.model.PageData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ExportDataModel
 * @author wqzhang
 * @Description 导出模板类 支持类型
 *  int 型
 *  string 型
 *  double 型
 *  特殊类型（例如状态，类型 等）
 *  时间 年月日 时分秒
 *  时间 年月日 时分
 *  时间 年月日
 * @Date 2017/12/26 14:22
 * @version 1.0.0
 */
public class ExportDataModel {

    /**
     * 默认构造列数
     */
    private final int normalSize = 16;

    /**
     * 导出文件名
     */
    private String fileName = "";
    /**
     * sql 关键字
     */
    private String[] keys = new String[normalSize];

    /**
     * 数据类型
     */
    private Type[] types = new Type[normalSize];

    /**
     * 字段名称
     */
    private String[] titles = new String[normalSize];

    /**
     * 字段转译规则Map
     */
    private Map<String, String> specialTypeMap = new HashMap<String, String>();

    /**
     * 总计Map
     */
    private Map<String, Object> totalMap = new HashMap<String, Object>();

    /**
     * 实际使用的列数
     */
    private int columnIndex = 0;

    /**
     * 列数组大小 用来防止数组越界判断
     */
    private int total = normalSize;

    /**
     * 数据
     */
    private List<PageData> datas;

    public int getColumsLength() {
        return columnIndex;
    }

    /**
     * @param titleName
     * @param sqlKey
     * @Description 增加列 操作 String类型
     */
    public void addColumn(String titleName, String sqlKey) {
        addColumn(titleName, sqlKey, Type.STRING);
    }

    /**
     * @param titleName
     * @param sqlKey
     * @param specialTypeStr
     * @Description 增加列 操作 特殊 类型（如状态,类型 等）
     */
    public void addColumn(String titleName, String sqlKey, String specialTypeStr) {
        addColumn(titleName, sqlKey, Type.SPECIAL, specialTypeStr);
    }

    /**
     * @param titleName
     * @param sqlKey
     * @param type
     * @Description 增加列 操作  基础类型和自定类型
     */
    public void addColumn(String titleName, String sqlKey, Type type) {
        addColumn(titleName, sqlKey, type, "");
    }

    /**
     * @param titleName      标头名
     * @param sqlKey         数据库对应字段
     * @param type           数据类型
     * @param specialTypeStr 特殊转换关系
     * @Description 增加列 执行实际操作
     */
    private void addColumn(String titleName, String sqlKey, Type type,
                           String specialTypeStr) {
        if (columnIndex >= total) {
            // logger.error("总列数小于");
            resetArraySize();
        }
        if (null != specialTypeStr && !"".equals(specialTypeStr)) {
            specialTypeMap.put(sqlKey, specialTypeStr);
        }
        keys[columnIndex] = sqlKey;
        titles[columnIndex] = titleName;
        types[columnIndex] = type;

        columnIndex++;
    }

    /**
     * @param sqlKey
     * @param value
     * @Description 增加总计行
     */
    public void addTotalColumn(String sqlKey, Object value) {
        totalMap.put(sqlKey, value);
    }

    /**
     * 添加数据
     *
     * @param datas
     * @Description TODO()
     */
    public void addData(List<PageData> datas) {
        this.datas = datas;
    }

    /**
     * 数组扩容
     *
     * @Description TODO()
     */
    private void resetArraySize() {
        String[] keysNew = new String[total + 1];
        System.arraycopy(keys, 0, keysNew, 0, keys.length);
        keys = keysNew;

        Type[] typesNew = new Type[total + 1];
        System.arraycopy(types, 0, typesNew, 0, types.length);
        types = typesNew;

        String[] titlesNew = new String[total + 1];
        System.arraycopy(titles, 0, titlesNew, 0, titles.length);
        titles = titlesNew;

        total = keys.length;
    }


    public String[] getKeys() {
        return keys;
    }

    public Type[] getTypes() {
        return types;
    }

    public String[] getTitles() {
        return titles;
    }

    public List<PageData> getDatas() {
        return datas;
    }

    public void setDatas(List<PageData> datas) {
        this.datas = datas;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Map<String, String> getSpecialTypeMap() {
        return specialTypeMap;
    }

    public Map<String, Object> getTotalMap() {
        return totalMap;
    }

    public int getIndex() {
        return columnIndex;
    }

    /**
     * 导出 数据类型 枚举类 具体参数请到Const类中定义
     *
     * @author wqzhang
     * @version 1.0.0
     * @ClassName Type
     * @Date 2017年11月28日
     */
    public static enum Type {
        INT,//int 型
        STRING,//string 型
        DOUBLE,// double 型
        SPECIAL,// 特殊类型（例如状态，类型 等）
        BIGDECIMAL,//自定义参数类型
        DATE_Y_M_D_H_M,//时间 年月日 时分
        DATE_Y_M_D,//时间 年月日 
        DATE_Y_M_D_H_M_S;//时间 年月日 时分秒
    }


}

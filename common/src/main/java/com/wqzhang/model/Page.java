package com.wqzhang.model;

import java.io.Serializable;

/**
 * Created by wqzhang on 2017/9/12.
 */
public class Page implements Serializable {

    private int showCount;

    private int currentPage;

    private int totalPage;

    private int totalResult;

    private int currentResult;

    private PageData pd = new PageData();

    private boolean entityOrField; // true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性

    private String pageStr; // 最终页面显示的底部翻页导航，详细见：getPageStr();

    public Page() {
        //默认分页十条数据
        this.showCount = 10;
    }

    public boolean isEntityOrField() {
        return entityOrField;
    }

    public void setEntityOrField(boolean entityOrField) {
        this.entityOrField = entityOrField;
    }

    public String getPageStr() {
        return pageStr;
    }

    public void setPageStr(String pageStr) {
        this.pageStr = pageStr;
    }

    public int getShowCount() {
        return showCount;
    }

    public void setShowCount(int showCount) {
        this.showCount = showCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        if (totalResult % showCount == 0) {
            totalPage = totalResult / showCount;
        } else {
            totalPage = totalResult / showCount + 1;
        }
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        if (totalResult % showCount == 0) {
            totalPage = totalResult / showCount;
        } else {
            totalPage = totalResult / showCount + 1;
        }
        this.totalResult = totalResult;
    }

    public int getCurrentResult() {
        return currentResult;
    }

    public void setCurrentResult(int currentResult) {
        this.currentResult = currentResult;
    }

    public PageData getPd() {
        return pd;
    }

    public void setPd(PageData pd) {
        this.pd = pd;
    }
}

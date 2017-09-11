package com.wqzhang.model;

import java.io.Serializable;

/**
 * Created by wqzhang on 2017/9/12.
 */
public class Page implements Serializable {
    /**
     * 分页的数量
     */
    private int showCount;
    /**
     * 当前页
     */
    private int currentPage;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 总结果数量
     */
    private int totalResult;

    /**
     * 当前记录起始索引
     */
    private int currentResult;

    private PageData pd = new PageData();

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
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
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

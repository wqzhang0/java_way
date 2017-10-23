package com.wqzhang.dao;

/**
 * Created by wqzhang on 2017/9/28.
 *
 * @author wqzhang
 */
public interface DAO {

    /**
     * 保存sql接口
     *
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    Object save(String str, Object obj) throws Exception;

    /**
     * sql更新接口
     *
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    Object update(String str, Object obj) throws Exception;

    /**
     * sql删除
     *
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    Object delete(String str, Object obj) throws Exception;

    /**
     * 根据sql查找返回list
     *
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    Object findForList(String str, Object obj) throws Exception;

    /**
     * 根据sql查询对象
     *
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    Object findForObject(String str, Object obj) throws Exception;

}

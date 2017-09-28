package com.wqzhang.dao;

/**
 * Created by wqzhang on 2017/9/28.
 */
public interface DAO {

    Object save(String str, Object obj) throws Exception;

    Object update(String str, Object obj) throws Exception;

    Object delete(String str, Object obj) throws Exception;

    Object findForList(String str, Object obj) throws Exception;

    Object findForObject(String str, Object obj) throws Exception;

}

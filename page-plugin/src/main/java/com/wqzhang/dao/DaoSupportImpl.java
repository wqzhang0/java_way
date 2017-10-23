package com.wqzhang.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by wqzhang on 2017/9/28
 *
 * @author wqzhang
 */
@Repository("daoSupport")
public class DaoSupportImpl implements DAO {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;


    @Override
    public Object save(String str, Object obj) throws Exception {
        return sqlSessionTemplate.insert(str, obj);
    }

    @Override
    public Object update(String str, Object obj) throws Exception {
        return sqlSessionTemplate.update(str, obj);
    }

    @Override
    public Object delete(String str, Object obj) throws Exception {
        return sqlSessionTemplate.delete(str, obj);
    }

    @Override
    public Object findForList(String str, Object obj) throws Exception {
        return sqlSessionTemplate.selectList(str, obj);
    }

    @Override
    public Object findForObject(String str, Object obj) throws Exception {
        return sqlSessionTemplate.selectOne(str, obj);
    }

}

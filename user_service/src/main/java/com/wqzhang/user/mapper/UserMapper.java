package com.wqzhang.user.mapper;

import com.wqzhang.model.PageData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by wqzhang on 2017/9/14.
 */
@Mapper
@Component("userMapper")
public interface UserMapper {

    void insertUser(PageData pd);


    PageData getUser(PageData pd);
}

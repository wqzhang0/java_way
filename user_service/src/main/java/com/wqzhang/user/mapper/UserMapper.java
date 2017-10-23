package com.wqzhang.user.mapper;

import com.wqzhang.model.Page;
import com.wqzhang.model.PageData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by wqzhang on 2017/9/14.
 */
@Mapper
@Component("userMapper")
public interface UserMapper {

    void insertUser(PageData pd);

    PageData getUser(PageData pd);

    List<PageData> listAllUser();

    List<PageData> userlistPage(Page page);

    PageData getUserByAccount(String acccount);

    List<PageData> listPermsByUserId(Long userId);

}

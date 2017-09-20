package com.wqzhang;

import com.wqzhang.model.Page;
import com.wqzhang.model.PageData;
import com.wqzhang.user.service.UserManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceApplicationTests {

    @Autowired
    UserManager userService;

    @Test
    public void contextLoads() {
//        listAllUser();

//        testPage();

        testPageDubbo();
    }


    public void testPage() {
        Page page = new Page();
        List<PageData> userTen = userService.userlistPageNoPage(page);
        System.out.print(userTen.size());
    }


    public void testPageDubbo() {
        Page page = new Page();
        userService.userlistPage(page);
//        System.out.print(userTen.size());
    }


    //    @Test
    public void listAllUser() {
        List<PageData> pageDataList = userService.listAllUser();
        for (PageData pd : pageDataList) {
            System.out.println(pd.get("ID") + "\t" + pd.getString("NAME"));
        }
    }


    //    @Test
    public void addUsers() {
        PageData userPd = new PageData();
        userPd.put("STUDENT_ID", 1);
//        userService.getUser(userPd);
        userPd.put("NAME", "王满");
        userService.insertUser(userPd);
        userPd.put("NAME", "王琳");
        userService.insertUser(userPd);
        userPd.put("NAME", "王锐");
        userService.insertUser(userPd);
        userPd.put("NAME", "王韬茫");
        userService.insertUser(userPd);
        userPd.put("NAME", "王九雏");
        userService.insertUser(userPd);
        userPd.put("NAME", "王子骏");
        userService.insertUser(userPd);
        userPd.put("NAME", "王之骏");
        userService.insertUser(userPd);
        userPd.put("NAME", "王楠");
        userService.insertUser(userPd);
        userPd.put("NAME", "王静雯");
        userService.insertUser(userPd);
        userPd.put("NAME", "王镜文");
        userService.insertUser(userPd);
        userPd.put("NAME", "王翠楠");
        userService.insertUser(userPd);
        userPd.put("NAME", "王镜雯");
        userService.insertUser(userPd);
        userPd.put("NAME", "王靖雯");
        userService.insertUser(userPd);
//        王涵涵 王兴 王淳曦 王雨微 王钧涵 王浩晏 王芊语 王乐怡 王皓月 王文田 王文田 王文田 王禹勋 王思卓 王国珍 王建 王天佑 王昕 王玥婷 王浩南 王玥雯 王天睿
//        王雨辰 王靖雯 王镜雯 王翠楠 王镜文 王静文 王静雯 王楠 王之骏 王子骏 王九雏 王韬茫 王麒鄄

        userPd.put("NAME", "王雨辰");
        userService.insertUser(userPd);
        userPd.put("NAME", "王芊语");
        userService.insertUser(userPd);
    }
}

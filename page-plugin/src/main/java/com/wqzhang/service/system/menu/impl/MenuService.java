package com.wqzhang.service.system.menu.impl;

import com.wqzhang.dao.DaoSupportImpl;
import com.wqzhang.model.PageData;
import com.wqzhang.service.system.menu.MenuManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wqzhang on 2017/9/28.
 *
 * @author wqzhang
 */
@Service("menuService")
public class MenuService implements MenuManager {

    @Resource(name = "daoSupport")
    private DaoSupportImpl dao;

    @Override
    public String list() throws Exception {
        StringBuffer sb = new StringBuffer();
        List<PageData> lists = (List<PageData>) dao.findForList("MenuMapper.list", "");
        if (lists.size() == 0) {
            return "";
        }

        List<PageData> dirctroys = (List<PageData>) dao.findForList("MenuMapper.listDirctory", "");
        sb.append("[");
        for (PageData dirPd : dirctroys) {

            sb.append("{ \"id\":\"").append(dirPd.get("id")).append("\",").append("\"text\":\"").append(dirPd.get("name")).append("\",");
            List<PageData> menus = (List<PageData>) dao.findForList("MenuMapper.listMenuByPId", dirPd.get("id"));
            sb.append("\"nodes\": [");
            for (PageData menusPd : menus) {
                sb.append("{\"id\":\"").append(menusPd.get("id")).append("\",").append("\"text\":\"").append(menusPd.get("name")).append("\"},");
            }
            sb.delete(sb.length() - 1, sb.length());
            sb.append("]},");
        }


        sb.delete(sb.length() - 1, sb.length());
        sb.append("]");


        return sb.toString();
//        return (List<PageData>) dao.findForList("MenuMapper.list", "");
    }

    @Override
    public String getFirstTreeId() throws Exception {
        List<PageData> lists = (List<PageData>) dao.findForList("MenuMapper.list", "");
        if (lists.size() == 0) {
            return "";
        }

        List<PageData> dirctroys = (List<PageData>) dao.findForList("MenuMapper.listDirctory", "");


        return String.valueOf(dirctroys.get(0).get("id"));
    }

    @Override
    public String menuList(String userAccount) throws Exception {
        List<PageData> lists = (List<PageData>) dao.findForList("MenuMapper.menuList", userAccount);

        return "";
    }


}

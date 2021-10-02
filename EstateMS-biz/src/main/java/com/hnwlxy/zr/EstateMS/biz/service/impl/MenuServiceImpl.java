package com.hnwlxy.zr.EstateMS.biz.service.impl;


import com.hnwlxy.zr.EstateMS.common.contants.GlobalVar;
import com.hnwlxy.zr.EstateMS.common.model.BaseModel;
import com.hnwlxy.zr.EstateMS.common.pojo.Menu;
import lombok.extern.slf4j.Slf4j;
import com.hnwlxy.zr.EstateMS.biz.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hnwlxy.zr.EstateMS.biz.service.MenuService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class MenuServiceImpl implements MenuService {
    @Autowired
     private MenuMapper menuMapper;

    /*
     * @title:<h3> 查询全部菜单 <h3>
     * @author: Zr
     * @date: 2021/1/18  15:33
     * @params
     * @return
     **/
    public void selectAllMenu() throws Exception {
        if (GlobalVar.mapCodeMenu==null){
            refreshSysMenu(new BaseModel());
        }

    }

    /*
     * @title:<h3> 刷新权限菜单 <h3>
     * @author: Zr
     * @date: 2021/1/18  15:33
     * @params [baseModel]
     * @return void
     **/
   public void refreshSysMenu(BaseModel baseModel) throws Exception{
       GlobalVar.mapCodeMenu=new HashMap<String,Menu>();
       GlobalVar.mapMenuIdCode=new HashMap<Integer, String>();
       List<Menu> list=menuMapper.selectAllMenu();
       for(int i=0;i<list.size();i++){
           GlobalVar.mapCodeMenu.put(list.get(i).getCode(),list.get(i));
           GlobalVar.mapMenuIdCode.put(list.get(i).getMenu_id(),list.get(i).getCode());
       }
       baseModel.setData(list);
   }


   /*
    * @title:<h3> 查询我的权限菜单 <h3>
    * @author: Zr
    * @date: 2021/1/18  15:33
    * @params [map, baseModel]
    * @return void
    **/

    public void findMyMenu(Map<String, String> map, BaseModel baseModel) throws Exception {
        selectAllMenu();
        Map<Integer,String> mapIdCode=new HashMap<Integer,String>();//菜单id和编号的map
        List<Menu> menuList1=new ArrayList<Menu>();//一级菜单列表
        List<Menu> menuList2=new ArrayList<Menu>();//二级菜单列表
        for(Map.Entry<String, String> entry:map.entrySet()){//遍历权限
            Menu menu=GlobalVar.mapCodeMenu.get(entry.getKey());
            if(menu==null){
                continue;
            }
            if(menu.getIs_leaf()==0){
                //一级菜单
                menuList1.add(menu);
            }else {
                //二级菜单
                menuList2.add(menu);
                //判断是否添加父级菜单
                if(menu.getFk_parent_id()!=0&&mapIdCode.get(menu.getFk_parent_id())==null){  //是二级并且未添加一级菜单
                    menuList1.add(GlobalVar.mapCodeMenu.get(GlobalVar.mapMenuIdCode.get(menu.getFk_parent_id())));//添加一级菜单
                    mapIdCode.put(menu.getFk_parent_id(),GlobalVar.mapMenuIdCode.get(menu.getFk_parent_id()));//标志一级菜单已添加
                }
            }
            mapIdCode.put(menu.getMenu_id(),menu.getCode());//标记该菜单已添加到返回值中

        }
        Map<String ,List<Menu>> result=new HashMap<String, List<Menu>>();
        result.put("menu_1",menuList1);
        result.put("menu_2",menuList2);
        baseModel.setData(result);
    }
}

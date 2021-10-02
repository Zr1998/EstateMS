package com.hnwlxy.zr.EstateMS.biz.service.impl;


import com.hnwlxy.zr.EstateMS.common.contants.GlobalVar;
import com.hnwlxy.zr.EstateMS.common.em.RoleMenuEnum;
import com.hnwlxy.zr.EstateMS.common.encrypt.MD5DES;
import com.hnwlxy.zr.EstateMS.common.model.BaseModel;
import com.hnwlxy.zr.EstateMS.common.vo.SysUserVo;
import lombok.extern.slf4j.Slf4j;
import com.hnwlxy.zr.EstateMS.biz.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hnwlxy.zr.EstateMS.biz.service.LoginService;
import com.hnwlxy.zr.EstateMS.biz.service.MenuService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuService menuService;
    /*
     * @title:<h3> 用户登录 <h3>
     * @author: Zr
     * @date: 2021/1/17  15:36
     * @params [user, baseModel]
     * @return void
     **/
    public void login(SysUserVo userVo, BaseModel baseModel) throws Exception {
        SysUserVo checkuser=userMapper.selectUserByAccount(userVo.getUser().getUser_account());
      //  SysUserVo user=userDao.selectUserVoByUserId(checkuser.getUser_id());
        if(checkuser==null){//如果用户信息不存在，说明账号不存在
            baseModel.setResultCode(1);
            baseModel.setMessage("账号或密码不能没空");
        }else {
            //判断密码是否正确
            if(checkuser.getUser().getPassword().equals(MD5DES.encrypt(userVo.getUser().getPassword()))){
                baseModel.setResultCode(0);
                baseModel.setMessage("登录成功");
                userVo.getUser().setUser_name(checkuser.getUser().getUser_name());
                userVo.setUser(checkuser.getUser());
                userVo.setRole_id(checkuser.getRole_id());
                userVo.getUser().setPassword(null);//密码等敏感数据不返回
                userVo.setUser_id(checkuser.getUser().getUser_id());
                baseModel.setData(userVo);
            }else {
                //账号或密码错误
                baseModel.setResultCode(1);
                baseModel.setMessage("账号或密码错误");
            }
        }
        int count=userMapper.updateBySessionId(userVo.getUser());
        if(count==0){
            baseModel.setResultCode(1);
            baseModel.setMessage("添加session_id失败");
        }
    }

    public void selectEnumRoleMenu(BaseModel baseModel) throws Exception {
        List<Map<String,String>> listMap=new ArrayList<Map<String,String>>();
        menuService.selectAllMenu();
        for(RoleMenuEnum em:RoleMenuEnum.values()){
            Map<String,String> map=new HashMap<String ,String>();
            map.put("name",em.getName());
            map.put("code",em.getCode()+"");
            map.put("roleValue",em.getValue());
            if(GlobalVar.mapCodeMenu.get(em.getCode()+"")!=null){//说明数据库和枚举类中同时存在该权限菜单
                listMap.add(map);
            }

        }
        baseModel.setData(listMap);
    }


}

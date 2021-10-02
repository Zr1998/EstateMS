package com.hnwlxy.zr.EstateMS.web.controller;


import com.hnwlxy.zr.EstateMS.web.baseController.BaseController;
import com.hnwlxy.zr.EstateMS.common.exception.BusinessException;
import com.hnwlxy.zr.EstateMS.common.model.BaseModel;
import com.hnwlxy.zr.EstateMS.common.pojo.UserRole;
import com.hnwlxy.zr.EstateMS.biz.service.UserService;
import com.hnwlxy.zr.EstateMS.common.vo.SysUserVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
public class RegisterController extends BaseController {
    public static final String DEFAULT_PASSWORD="111111"; //默认密码
    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户注册",httpMethod = "POST",response = SysUserVo.class)
    @RequestMapping(value = "regist",method = RequestMethod.POST)
    public BaseModel regist(@ModelAttribute("")SysUserVo userVo, BaseModel baseModel) throws Exception{
        if(userVo==null){
            throw  new BusinessException("用户信息不能为空");
        }else if(userVo.getUser().getUser_account()==null || "".equals(userVo.getUser().getUser_account())){
            throw  new BusinessException("账号不能为空");
        }else if(userVo.getUser().getPassword()==null || "".equals(userVo.getUser().getPassword())){
            throw  new BusinessException("用户密码不能为空");
        }else if(userVo.getUser().getUser_name()==null || "".equals(userVo.getUser().getUser_name())){
            throw  new BusinessException("用户姓名不能为空");
        }
        List<UserRole> userRoleList=new ArrayList<UserRole>();
        UserRole userRole=new UserRole();
        userRole.setFk_role_id(1);
        userRole.setFk_user_id(userVo.getUser().getUser_id());
        userRoleList.add(0,userRole);
        userVo.setListUserRole(userRoleList);
        userVo.getUser().setCreate_user_name(userVo.getUser().getUser_name());
        userService.registUserVo(baseModel,userVo);
        baseModel.setMessage("用户注册成功");
        baseModel.setTempMFile(null);
        return baseModel;
    }
}

package com.hnwlxy.zr.EstateMS.web.controller;


import com.hnwlxy.zr.EstateMS.web.baseController.BaseController;
import com.hnwlxy.zr.EstateMS.common.contants.BaseContants;
import com.hnwlxy.zr.EstateMS.common.em.ResultMesgEnum;
import com.hnwlxy.zr.EstateMS.common.exception.BusinessException;
import com.hnwlxy.zr.EstateMS.common.model.BaseModel;
import com.hnwlxy.zr.EstateMS.common.pojo.Menu;
import com.hnwlxy.zr.EstateMS.common.rsa.RSAJS;
import com.hnwlxy.zr.EstateMS.biz.service.LoginService;
import com.hnwlxy.zr.EstateMS.biz.service.MenuService;
import com.hnwlxy.zr.EstateMS.biz.service.RoleService;
import com.hnwlxy.zr.EstateMS.common.vo.SysUserVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller
@RequestMapping("/index")
public class LoginController extends BaseController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;


    @ApiOperation(value = "登录",notes = "登录",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user.user_account",value = "账号",
                    dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "user.password",value = "密码",
                    dataType = "String",paramType = "query")
    })
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public BaseModel login(SysUserVo userVo, BaseModel baseModel) throws Exception{
        //模拟空指针异常，即系统异常
//        loginlog=null;
//        loginlog.getUser_password();
        //模拟业务异常
//        if(loginlog!=null){
//            throw new BusinessException("我抛出的异常");
//        }
        if(userVo.getUser()==null){
            //throw new BusinessException("账号异常");
            throw new BusinessException(ResultMesgEnum.ACCOUT_ERROR);
        }else if(userVo.getUser().getUser_account()==null|| "".equals(userVo.getUser().getUser_account())
                ||userVo.getUser().getPassword()==null|| "".equals(userVo.getUser().getPassword())){
            //throw new BusinessException("账号或密码不能为空");
            throw  new BusinessException(ResultMesgEnum.LOGIN_USER);
        }else {
            userVo.getUser().setSession_id(session.getId());
            //解密
            try{
                String password=RSAJS.sessionDecryptHexStr(session,userVo.getUser().getPassword());
                userVo.getUser().setPassword(password);
            }catch (Exception e){
                throw new BusinessException(ResultMesgEnum.LOGIN_PASSWORD);
            }
            loginService.login(userVo,baseModel);
            session.setAttribute(BaseContants.LOGIN_USER,baseModel.getData());
            if(userVo.getUser().getUser_id()==null){
                userVo.getUser().setUser_id(0);
            }
         session.setAttribute(BaseContants.LOGIN_PERMISSION,roleService.selectMergeRolePermissionByUserId(getSessionUser().getUser().getUser_id()));
        }
        return baseModel;
    }



    /*
     * @title:<h3> 查询权限菜单枚举类 <h3>
     * @author: Zr
     * @date: 2021/1/14  11:02
     * @params
     * @return
     **/
    @ApiOperation(value = "查询权限菜单枚举类", httpMethod = "GET")
    @RequestMapping("/selectEnumRoleMenu")
    public BaseModel selectEnumRoleMenu(BaseModel baseModel) throws  Exception{
        loginService.selectEnumRoleMenu(baseModel);
        baseModel.setMessage("查询角色权限成功");
        return baseModel;
    }


    /*
     * @title:<h3> 刷新权限菜单 <h3>
     * @author: Zr
     * @date: 2021/1/14  11:02
     * @params [baseModel]
     **/
    @ApiOperation(value = "刷新权限菜单",httpMethod = "GET")
    @RequestMapping("/refreshSysMenu")
    public BaseModel refreshSysMenu(BaseModel baseModel) throws Exception{
        menuService.refreshSysMenu(baseModel);
        baseModel.setMessage("刷新权限菜单成功");
        return baseModel;
    }

    /*
     * @title:<h3> 查询当前用户权限菜单 <h3>
     * @author: Zr
     * @date: 2021/1/14  11:02
     * @params [baseModel]
     **/
    @ApiOperation(value = "查询当前用户权限菜单",httpMethod = "GET",response = Menu.class)
    @RequestMapping("findMyMenu")
    public BaseModel findMyMenu(BaseModel baseModel)throws Exception{
        Map<String,String > map=(Map<String, String>)session.getAttribute(BaseContants.LOGIN_PERMISSION);
        if(map==null){
            throw  new BusinessException(ResultMesgEnum.NO_LOGIN);
        }
        menuService.findMyMenu(map,baseModel);
        baseModel.setMessage("查询权限菜单成功");
        return baseModel;
    }

    @ApiOperation(value = "注销",httpMethod = "GET")
    @RequestMapping("loginOut")
    public BaseModel loginOut(SysUserVo userVo,BaseModel baseModel) throws Exception{
        userVo=getSessionUser();
        userVo.getUser().setSession_id(session.getId());
//        loginService.loginOut(userVo);
        session.invalidate();
        baseModel.setMessage("注销成功");
        return baseModel;
    }
}

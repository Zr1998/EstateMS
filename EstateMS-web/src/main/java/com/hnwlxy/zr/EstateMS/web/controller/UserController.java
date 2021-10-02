package com.hnwlxy.zr.EstateMS.web.controller;


import com.hnwlxy.zr.EstateMS.biz.service.UserService;
import com.hnwlxy.zr.EstateMS.common.contants.BaseContants;
import com.hnwlxy.zr.EstateMS.web.aop.AopOperation;
import com.hnwlxy.zr.EstateMS.web.aop.AopSubmit;
import com.hnwlxy.zr.EstateMS.web.baseController.BaseController;
import com.hnwlxy.zr.EstateMS.common.em.ResultMesgEnum;
import com.hnwlxy.zr.EstateMS.common.em.RoleMenuEnum;
import com.hnwlxy.zr.EstateMS.common.encrypt.MD5DES;
import com.hnwlxy.zr.EstateMS.common.exception.BusinessException;
import com.hnwlxy.zr.EstateMS.common.model.BaseModel;
import com.hnwlxy.zr.EstateMS.common.pojo.User;
import com.hnwlxy.zr.EstateMS.common.util.UploderFileUtil;
import com.hnwlxy.zr.EstateMS.common.vo.SysUserVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("sysUser")
public class UserController extends BaseController {
    public static final String DEFAULT_PASSWORD="111111"; //默认密码

    @Autowired
    private UserService userService;

    @RequestMapping("/selectPageUserVo")
    @ResponseBody
    @AopOperation(menu = RoleMenuEnum.NO_202,type = BaseContants.OPERATION_TYPE.SEARCH)
    @ApiOperation(value = "分页查询用户列表",notes = "查询",httpMethod = "POST",response = SysUserVo.class)
    public BaseModel selectPageUser(@ModelAttribute("baseModel")BaseModel baseModel) throws  Exception{
        userService.selectPageUserVo(baseModel);
        baseModel.setMessage("用户列表查询成功");
        return baseModel;
    }

    @AopSubmit
    @AopOperation(menu=RoleMenuEnum.NO_202,type = BaseContants.OPERATION_TYPE.ADD)
    @ApiOperation(value = "新增用户信息",httpMethod = "POST",response = SysUserVo.class)
    @RequestMapping("insertUserVo")
    public BaseModel insertUserVo(BaseModel baseModel, @ModelAttribute("") SysUserVo userVo) throws Exception{
        if(userVo==null){
            throw  new BusinessException("用户信息不能为空");
        }else if(userVo.getUser().getUser_account()==null || "".equals(userVo.getUser().getUser_account())){
            throw  new BusinessException("账号不能为空");
        }else if(userVo.getUser().getUser_name()==null || "".equals(userVo.getUser().getUser_name())){
            throw  new BusinessException("用户姓名不能为空");
        }
        userVo.getUser().setPassword(MD5DES.encrypt(DEFAULT_PASSWORD));
        userVo.getUser().setCreate_user_id(getSessionUser().getUser().getUser_id());
        userVo.getUser().setCreate_user_name(getSessionUser().getUser().getUser_name());
        userService.inserUserVo(userVo,baseModel);
        baseModel.setMessage("用户新增成功");
        baseModel.setTempMFile(null);
        return baseModel;
    }

    @AopOperation(menu=RoleMenuEnum.NO_202,type = BaseContants.OPERATION_TYPE.SEARCH)
    @ApiOperation(value = "根据用户id查询用户详情", httpMethod = "GET",response = SysUserVo.class)
    @RequestMapping("/selectUserVoByUserId/{userId}")
    public BaseModel selectUserVoByUserId(BaseModel  baseModel,@PathVariable("userId") int userId) throws Exception{
        userService.selectUserVoByUserId(baseModel,userId);
        baseModel.setMessage("根据用户id查询用户详情成功");
        return baseModel;
    }

    @AopSubmit
    @AopOperation(menu = RoleMenuEnum.NO_202,type = BaseContants.OPERATION_TYPE.UPDATE)
    @ApiOperation(value = "修改用户信息",notes = "修改用户信息",httpMethod = "POST",response = SysUserVo.class)
    @RequestMapping(value="updateUserVo",method =RequestMethod.POST)
    public BaseModel updateUserVo(SysUserVo userVo,BaseModel baseModel) throws Exception{
        if(userVo == null || userVo.getUser()==null) {
            throw new BusinessException("用户信息不能为空");
        }else if(userVo.getUser().getUser_id()==0){
            throw new BusinessException("用户id不能为空");
        }else if(userVo.getUser().getUser_account() == null || "".equals(userVo.getUser().getUser_account())){
            throw new BusinessException("账号不能为空");
        }else if(userVo.getUser().getUser_name() == null || "".equals(userVo.getUser().getUser_name())){
            throw new BusinessException("用户姓名不能为空");
        }
        userVo.getUser().setUpdate_user_id(getSessionUser().getUser_id());
        userVo.getUser().setUpdate_user_name(getSessionUser().getUser().getUser_name());
        userService.udateUserVo(userVo,baseModel);
        baseModel.setMessage("修改用户信息成功");
        baseModel.setTempMFile(null);
        baseModel.setAop_mesg("用户id:"+userVo.getUser().getUser_id()+"，姓名："+userVo.getUser().getUser_name());
        return baseModel;
    }


    @AopOperation(menu = RoleMenuEnum.NO_202,type = BaseContants.OPERATION_TYPE.DELETE)
    @ApiOperation(value = "删除用户信息",httpMethod = "GET")
    @RequestMapping("deleteUser/{ids}")
    public BaseModel deleteUserById(BaseModel baseModel,@PathVariable("ids") String userIds) throws Exception {
        getSessionUser();
        userService.deleteUserInPk(userIds,baseModel);
        baseModel.setAop_mesg(userIds);
        return baseModel;
    }

    @AopOperation(desc = "修改当前管理员密码",menu = RoleMenuEnum.NO_202,type = BaseContants.OPERATION_TYPE.UPDATE)
    @RequestMapping("updatePassword")
    public BaseModel updatePassword(SysUserVo userVo,BaseModel baseModel) throws Exception {
        if(userVo==null ||userVo.getUser()==null){
            throw new BusinessException("用户密码不能为空");
        }
        userVo.getUser().setUser_id(getSessionUser().getUser().getUser_id());
        userVo.getUser().setUpdate_user_id(getSessionUser().getUser().getUser_id());
        userVo.getUser().setUpdate_user_name(getSessionUser().getUser().getUser_name());
        userService.updatePassword(userVo,baseModel);
        baseModel.setMessage("修改密码成功");
        return baseModel;
    }


    @AopOperation(desc = "重置密码",menu = RoleMenuEnum.NO_202,type = BaseContants.OPERATION_TYPE.UPDATE)
    @ApiOperation(value = "重置用户密码",httpMethod = "GET")
    @RequestMapping("resetPassword/{userId}")
    public BaseModel resetPassword(BaseModel baseModel,@PathVariable("userId") int userId) throws Exception {
        User user = new User();
        user.setUser_id(userId);
        user.setUpdate_user_id(getSessionUser().getUser_id());
        user.setUpdate_user_name(getSessionUser().getUser().getUser_name());
        user.setPassword(MD5DES.encrypt(DEFAULT_PASSWORD));
        userService.resetPassword(user,baseModel);
        baseModel.setMessage("重置密码成功");
        baseModel.setAop_mesg(userId+"");
        return baseModel;
    }


    /*
     * @title:<h3> 查询当前登录用户信息 <h3>
     * @author: Zr
     * @date: 2021/1/20  10:49
     * @params [baseModel]
     **/
    @ApiOperation(value = "查询当前登录用户信息",httpMethod = "GET")
    @RequestMapping("findMyUserVo")
    public BaseModel findMyUserVo(BaseModel baseModel) throws Exception{
        userService.selectUserVoByUserId(baseModel,getSessionUser().getUser_id());
        return baseModel;
    }


    @ApiOperation(value = "修改当前登录用户信息",httpMethod = "POST",response =SysUserVo.class)
    @RequestMapping("updateMyProFile")
    public BaseModel updateMyProFile(SysUserVo userVo,BaseModel baseModel) throws Exception{
        //userVo=getSessionUser();
        if(userVo==null ||userVo.getUser()==null){
            throw new BusinessException("用户信息不能为空");
        }
        userVo.getUser().setUpdate_user_id(getSessionUser().getUser().getUser_id());
        userVo.getUser().setUpdate_user_name(getSessionUser().getUser().getUser_name());
        userVo.getUser().setUser_id(getSessionUser().getUser().getUser_id());
        userService.updateMyUserVo(userVo,baseModel);
        baseModel.setTempMFile(null);
        baseModel.setMessage("更新个人信息成功");
        return baseModel;
    }
    /*
     * @title:<h3> 导出用户信息 <h3>
     * @author: Zr
     * @date: 2021/1/20  10:49
     * @params [baseModel]
     * @return void
     **/
    @AopOperation(menu = RoleMenuEnum.NO_202,type = BaseContants.OPERATION_TYPE.EXPORT)
    @ApiOperation(value = "导出用户信息",httpMethod = "POST")
    @RequestMapping("expUserDTO")
    public void expUserDTO(BaseModel baseModel)throws Exception{
        String destPath=userService.exportUserDTO(baseModel);
        UploderFileUtil.downFile(response,destPath,"用户信息表.xls");
    }
/*
 * @title:<h3> 导入用户 <h3>
 * @author: Zr
 * @date: 2021/1/20  10:49
 * @params [baseModel]
 * @return void
 **/
    @AopOperation(menu = RoleMenuEnum.NO_202,type = BaseContants.OPERATION_TYPE.IMPORT)
    @ApiOperation(value = "导入用户信息",httpMethod = "POST")
    @RequestMapping("impUserDTO")
    public BaseModel impUserDTO(BaseModel baseModel)throws Exception{
        userService.impUserDTO(getSessionUser().getUser(),baseModel);
        return baseModel;
    }

    @ApiOperation(value = "更新个人信息",httpMethod = "POST",response =SysUserVo.class)
    @RequestMapping("updateMyUserVo")
    public BaseModel updateMyUserVo(SysUserVo userVo, BaseModel baseModel) throws Exception{
        if(userVo==null ||userVo.getUser()==null){
            throw new BusinessException("用户信息不能为空");
        }
        userVo.getUser().setUpdate_user_id(getSessionUser().getUser().getUser_id());
        userVo.getUser().setUpdate_user_name(getSessionUser().getUser().getUser_name());
        userVo.getUser().setUser_id(getSessionUser().getUser().getUser_id());
        userService.updateMyUserVo(userVo,baseModel);
        baseModel.setTempMFile(null);
        baseModel.setMessage("更新个人信息成功");
        return baseModel;
    }
}



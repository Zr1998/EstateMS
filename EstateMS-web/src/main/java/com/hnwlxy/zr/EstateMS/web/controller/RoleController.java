package com.hnwlxy.zr.EstateMS.web.controller;


import com.hnwlxy.zr.EstateMS.common.contants.BaseContants;
import com.hnwlxy.zr.EstateMS.common.em.RoleMenuEnum;
import com.hnwlxy.zr.EstateMS.web.aop.AopOperation;
import com.hnwlxy.zr.EstateMS.web.aop.AopSubmit;
import com.hnwlxy.zr.EstateMS.web.baseController.BaseController;
import com.hnwlxy.zr.EstateMS.common.exception.BusinessException;
import com.hnwlxy.zr.EstateMS.common.model.BaseModel;
import com.hnwlxy.zr.EstateMS.common.pojo.Role;
import com.hnwlxy.zr.EstateMS.biz.service.RoleService;
import com.hnwlxy.zr.EstateMS.common.vo.SysRoleVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {
    private static Logger log=Logger.getLogger(RoleController.class);
    @Autowired
    private RoleService roleService;

    @RequestMapping("/selectPageRole")
    @ResponseBody
    @AopOperation(menu = RoleMenuEnum.NO_201,type = BaseContants.OPERATION_TYPE.SEARCH)
    @ApiOperation(value = "分页查询角色列表",notes = "查询",httpMethod = "POST",response = SysRoleVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "baseModel.queryParams.curr_page",value ="当前页码",dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "baseModel.queryParams.page_size",value ="每页显示的记录数",dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "baseModel.queryParams.where",value ="查询条件",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "baseModel.queryParams.order",value ="排序",dataType = "String",paramType = "query")})
    public BaseModel selectPageRole(@ModelAttribute("baseModel")BaseModel baseModel) throws  Exception{
        roleService.selectPageRole(baseModel);
        baseModel.setMessage("角色列表查询成功");
        return baseModel;
    }


     @AopSubmit
    @AopOperation(menu = RoleMenuEnum.NO_201,type = BaseContants.OPERATION_TYPE.ADD)
    @ResponseBody
    @ApiOperation(value = "新增角色",  httpMethod = "POST",response = SysRoleVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "listRolePermission[0].code", value = "权限编号", defaultValue = "String", paramType = "query"),
            @ApiImplicitParam(name = "listRolePermission[0].permission_value", value = "权限值", defaultValue = "String", paramType = "query")
    })
    @RequestMapping(value = "/insertRoleVo",method =RequestMethod.POST)
    public BaseModel insertRoleVo(@ModelAttribute SysRoleVo roleVo, BaseModel baseModel) throws Exception {
        if (roleVo==null){
            throw new BusinessException("请输入角色信息");
        }else if (roleVo.getRole()==null){
            throw new BusinessException("请输入角色信息");
        }else if (roleVo.getRole().getName()==null||"".equals(roleVo.getRole().getName())){
            throw new BusinessException("请输入角色名称");
        }else if (roleVo.getListRolePermission()==null||roleVo.getListRolePermission().size()==0){
            throw new BusinessException("请输入角色权限");
        }
        roleVo.getRole().setCreate_user_id(getSessionUser().getUser_id());
        roleVo.getRole().setCreate_user_name(getSessionUser().getUser().getUser_name());
        roleService.insertRoleVo(roleVo,baseModel);
        baseModel.setMessage("角色新增成功");
        return baseModel;
    }

    /*
     * @title:<h3> 根据角色id查询角色详情 <h3>
     * @author: Zr
     * @date: 2021/1/14  10:49
     * @params [baseModel, pk]
     **/
    @AopOperation(menu = RoleMenuEnum.NO_201,type = BaseContants.OPERATION_TYPE.SEARCH)
    @ApiOperation(value = "根据角色id查询角色详情", httpMethod = "GET")
    @RequestMapping("/selectRoleVoById/{pk}")
    public BaseModel selectRoleVoByPk(BaseModel  baseModel,@PathVariable("pk") int pk) throws Exception{
        roleService.selectRoleVoByPk(baseModel,pk);
        baseModel.setMessage("根据角色id查询角色详情成功");
        return baseModel;
    }

    /*
     * @title:<h3> 修改角色信息 <h3>
     * @author: Zr
     * @date: 2021/1/14  10:49
     * @params [roleVo, baseModel]
     **/
    @AopSubmit
    @AopOperation(menu = RoleMenuEnum.NO_201,type = BaseContants.OPERATION_TYPE.UPDATE)
    @ApiOperation(value = "修改角色信息",  httpMethod = "POST",response = SysRoleVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "listRolePermission[0].code", value = "权限编号", defaultValue = "String", paramType = "query"),
            @ApiImplicitParam(name = "listRolePermission[0].permission_value", value = "权限值", defaultValue = "String", paramType = "query")
    })
    @RequestMapping(value = "/updateRoleVo",method =RequestMethod.POST)
    public BaseModel updateRoleVo(@ModelAttribute SysRoleVo roleVo, BaseModel baseModel) throws Exception {
        if (roleVo==null){
            throw new BusinessException("请输入角色信息");
        }else if (roleVo.getRole()==null){
            throw new BusinessException("请输入角色信息");
        }else if (roleVo.getRole().getName()==null||"".equals(roleVo.getRole().getName())){
            throw new BusinessException("请输入角色名称");
        }else if (roleVo.getListRolePermission()==null||roleVo.getListRolePermission().size()==0){
            throw new BusinessException("请选择角色权限");
        }
        roleVo.getRole().setCreate_user_id(getSessionUser().getUser_id());
        roleVo.getRole().setCreate_user_name(getSessionUser().getUser().getUser_name());
        roleService.updateRoleVo(roleVo,baseModel);
        baseModel.setMessage("角色修改成功");
        return baseModel;
    }

    @AopOperation(menu = RoleMenuEnum.NO_201,type = BaseContants.OPERATION_TYPE.DELETE)
    @ApiOperation(value = "删除角色信息",httpMethod = "GET")
    @RequestMapping("deleteRole/{ids}")
    public BaseModel updateRoleVo(BaseModel baseModel,@PathVariable("ids") String roleIds) throws Exception {
        getSessionUser();
        roleService.deleteRoleInPk(roleIds,baseModel);
        return baseModel;
    }

    /*
     * @title:<h3> 查询角色名称 <h3>
     * @author: Zr
     * @date: 2021/1/14  11:02
     * @params [baseModel]
     **/
    @ApiOperation(value = "查询角色名称",httpMethod = "GET",response = Role.class)
    @RequestMapping("selectRoleName")
    public BaseModel selectRoleName(BaseModel baseModel) throws Exception{
        roleService.selectRoleName(baseModel);
        baseModel.setMessage("查询角色名称成功");
        return  baseModel;
    }
}

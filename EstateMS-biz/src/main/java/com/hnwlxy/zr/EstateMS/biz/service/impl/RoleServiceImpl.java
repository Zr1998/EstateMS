package com.hnwlxy.zr.EstateMS.biz.service.impl;



import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnwlxy.zr.EstateMS.common.em.ResultMesgEnum;
import com.hnwlxy.zr.EstateMS.common.exception.BusinessException;
import com.hnwlxy.zr.EstateMS.common.model.BaseModel;
import com.hnwlxy.zr.EstateMS.common.pojo.Role;
import com.hnwlxy.zr.EstateMS.common.pojo.RolePermission;
import com.hnwlxy.zr.EstateMS.common.vo.SysRoleVo;
import lombok.extern.slf4j.Slf4j;
import com.hnwlxy.zr.EstateMS.biz.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hnwlxy.zr.EstateMS.biz.service.RoleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    /*
     * @title:<h3> 分页查询角色列表 <h3>
     * @author: Zr
     * @date: 2021/1/17  15:33
     * @params [baseModel]
     * @return void
     **/
    public void selectPageRole(BaseModel baseModel) throws Exception {
        PageHelper.startPage(baseModel.getQueryParams().getCurr_page(), baseModel.getQueryParams().getPage_size());
//        baseModel.getQueryParams().setWhere("name like '%张三%' and memo like '%备注%'");
        List<Role> roles = roleMapper.selectRole(baseModel.getQueryParams());
        PageInfo roleliset = new PageInfo(roles);
        baseModel.setData(roleliset);
    }

    public void insertRoleVo(SysRoleVo sysRoleVo, BaseModel baseModel)throws Exception {
        //1.判断角色信息是否已存在，若不存在则新增，否则抛出角色信息已存在异常

        int count = roleMapper.countRoleByName(sysRoleVo.getRole().getName());
        if (count > 0) {
            throw new BusinessException("角色已存在，请勿重复添加");
        }

        //2.新增角色信息
        roleMapper.insertRole(sysRoleVo.getRole());

        //3.或得新增角色权限信息的id，保存在角色权限中，新增角色权限信息
        for (int i = 0; i < sysRoleVo.getListRolePermission().size(); i++) {
            RolePermission rolePermission = sysRoleVo.getListRolePermission().get(i);
            rolePermission.setFk_role_id(sysRoleVo.getRole().getRole_id());
            rolePermission.setCreate_user_id(sysRoleVo.getRole().getCreate_user_id());
            rolePermission.setCreate_user_name(sysRoleVo.getRole().getCreate_user_name());

            //4.新增角权限信息,如果全是权限值全部为0.则不保存权限信息
            if (rolePermission.getPermission_value() != null && rolePermission.getPermission_value().indexOf("1") > -1) {
                roleMapper.insertRolePermission(rolePermission);
            }
        }
    }

    /*
     * @title:<h3> 根据角色id查询角色详情 <h3>
     * @author: Zr
     * @date: 2021/1/17  15:33
     * @params [baseModel]
     * @return void
     **/
    public void selectRoleVoByPk(BaseModel baseModel,int pk) throws Exception {
        SysRoleVo roleVo=roleMapper.selectRoleVoByPk(pk);
        if (roleVo==null) {
            throw new BusinessException("该角色信息已被删除");
        }
        baseModel.setData(roleVo);
    }


    /*
     * @title:<h3> 修改角色信息 <h3>
     * @author: Zr
     * @date: 2021/1/17  15:33
     * @params [roleVo, baseModel]
     * @return void
     **/
    public void updateRoleVo(SysRoleVo roleVo,BaseModel baseModel) throws Exception{
        //1.判断角色名称是否重复，根据角色名称查询角色信息
            Role oldRole=roleMapper.selectRoleByName(roleVo.getRole().getName());
            if(oldRole==null){ //说明角色名称不存在（不重复）

            }else if(oldRole.getRole_id()==roleVo.getRole().getRole_id()){ //说明角色未重复，当前角色名称未发生变化

            }else {
                //2.如果存在角色信息，则判断查询到的角色id和当前id是否一致，如果不一致，则说明角色名称重复
                throw new BusinessException("角色名称 '" + roleVo.getRole().getName() + "' 已存在，请勿重复添加");
            }
            //3.更新角色信息
            int count=roleMapper.updateRoleVo(roleVo.getRole());
            if(count==0){
                throw new BusinessException(ResultMesgEnum.UPDATE_VES);
            }
            //4.删除原有角色权限
        roleMapper.deleteRolePermission(oldRole.getRole_id()+"");
        //5.添加角色权限信息
        for(int i=0;i<roleVo.getListRolePermission().size();i++){
            RolePermission rolePermission = roleVo.getListRolePermission().get(i);
            rolePermission.setFk_role_id(roleVo.getRole().getRole_id());
            rolePermission.setCreate_user_id(roleVo.getRole().getCreate_user_id());
            rolePermission.setCreate_user_name(roleVo.getRole().getCreate_user_name());

            if (rolePermission.getPermission_value() != null && rolePermission.getPermission_value().indexOf("1") > -1) {
                roleMapper.insertRolePermission(rolePermission);
            }
        }
    }


    /*
     * @title:<h3> 根据角色id删除角色信息 <h3>
     * @author: Zr
     * @date: 2021/1/17  15:33
     * @params [roleIds, baseModel]
     * @return void
     **/
    public void deleteRoleInPk(String roleIds, BaseModel baseModel) throws Exception {
        //删除角色权限表记录
        roleMapper.deleteRolePermission(roleIds);
        //删除角色信息
        int count=roleMapper.removeRoleInPk(roleIds);
        // 根据角色id删除用户角色关系
        int num=roleMapper.deleteUserRoleInRoleIds(roleIds);
        baseModel.setMessage("删除"+count+"条角色信息成功");
    }


    public void selectRoleName(BaseModel baseModel) throws Exception {
        baseModel.setData(roleMapper.selectRoleName());
    }

    /*
     * @title:<h3> 根据用户id查询并合并权限值 <h3>
     * @author: Zr
     * @date: 2021/1/17  15:33
     * @params [userId]
     * @return java.util.Map<java.lang.String,com.dc.pojo.Rolepermission>
     **/
    public  Map<String,String> selectMergeRolePermissionByUserId(int userId) throws Exception{
       // List<Rolepermission> listResult=new ArrayList<Rolepermission>();
        Map<String,String> map=new HashMap<String, String>();
        List<RolePermission> list=roleMapper.selectRolePermissionByUserId(userId);
        for(RolePermission permission:list){
            if(map.get(permission.getCode())==null){ //如果map中不存在编号
                map.put(permission.getCode(), permission.getPermission_value());
            }else{  //如果已存在权限编号，则进行权限合并
                 StringBuilder mapValue=new StringBuilder(map.get(permission.getCode()));
                 StringBuilder perValue=new StringBuilder(permission.getPermission_value());
                 if(mapValue.length()>=perValue.length()){
                     //100111           1101
                     for (int i=0;i<perValue.length();i++){  //循环短的
                         if(mapValue.charAt(i)=='0' && perValue.charAt(i)=='1'){
                             mapValue.replace(i,i+1,"1");
                         }
                     }
                     map.put(permission.getCode(),mapValue.toString());
                 }else{ //1100    111101
                     for (int i=0;i<mapValue.length();i++){  //循环短的
                         if(perValue.charAt(i)=='0' && mapValue.charAt(i)=='1'){
                             perValue.replace(i,i+1,"1");
                         }
                     }
                     map.put(permission.getCode(),perValue.toString());
                 }
            }
        }
        return map;
    }

}

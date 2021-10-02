package com.hnwlxy.zr.EstateMS.common.vo;



import com.hnwlxy.zr.EstateMS.common.pojo.Role;
import com.hnwlxy.zr.EstateMS.common.pojo.RolePermission;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/*
 * @title:<h3> 角色Vo对象 <h3>
 * @author: Zr
 * @date: 2020/12/7  13:35
 * @params
 * @return
 **/
public class SysRoleVo {
    @ApiModelProperty("角色信息")
    private Role role;
    @ApiModelProperty("角色权限信息")
    private List<RolePermission> listRolePermission;
    @ApiModelProperty("角色id")
    private int role_id;

    public List<RolePermission> getListRolePermission() {
        return listRolePermission;
    }

    public void setListRolePermission(List<RolePermission> listRolePermission) {
        this.listRolePermission = listRolePermission;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }
}

package com.hnwlxy.zr.EstateMS.common.vo;



import com.hnwlxy.zr.EstateMS.common.pojo.FileLog;
import com.hnwlxy.zr.EstateMS.common.pojo.Role;
import com.hnwlxy.zr.EstateMS.common.pojo.User;
import com.hnwlxy.zr.EstateMS.common.pojo.UserRole;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/*
 * @title:<h3> 用户Vo <h3>
 * @author: Zr
 * @date: 2020/12/7  13:35
 * @params
 * @return
 **/
public class SysUserVo {
    @ApiModelProperty("用户id")
    private int user_id;

    @ApiModelProperty("角色名称")
    private String role_names;

    @ApiModelProperty("用户对象")
    private User user;

    @ApiModelProperty("修改密码时存储旧密码")
    private String old_password;

    @ApiModelProperty("角色信息")
    private List<Role> listRole;

    @ApiModelProperty("用户角色关系对象")
    private List<UserRole> listUserRole;

    @ApiModelProperty("角色id")
    private int role_id;

    @ApiModelProperty("头像文件信息")
    private FileLog fileLog;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UserRole> getListUserRole() {
        return listUserRole;
    }

    public void setListUserRole(List<UserRole> listUserRole) {
        this.listUserRole = listUserRole;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getRole_names() {
        return role_names;
    }

    public void setRole_names(String role_names) {
        this.role_names = role_names;
    }

    public String getOld_password() {
        return old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public List<Role> getListRole() {
        return listRole;
    }

    public void setListRole(List<Role> listRole) {
        this.listRole = listRole;
    }

    public FileLog getFileLog() {
        return fileLog;
    }

    public void setFileLog(FileLog fileLog) {
        this.fileLog = fileLog;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }
}

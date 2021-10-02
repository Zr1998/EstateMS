package com.hnwlxy.zr.EstateMS.common.pojo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class RolePermission implements Serializable {
    @ApiModelProperty(value = "权限自增id")
    private Integer permission_id;

    @ApiModelProperty(value = "角色id")
    private Integer fk_role_id;

    @ApiModelProperty(value = "二级菜单编号")
    private String code;

    @ApiModelProperty(value = "权限值")
    private String permission_value;

    @ApiModelProperty(value = "创建人用户id")
    private Integer create_user_id;

    @ApiModelProperty(value = "创建人用户名")
    private String create_user_name;

    @ApiModelProperty(value = "创建时间")
    private Date create_time;

    public Integer getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(Integer permission_id) {
        this.permission_id = permission_id;
    }

    public Integer getFk_role_id() {
        return fk_role_id;
    }

    public void setFk_role_id(Integer fk_role_id) {
        this.fk_role_id = fk_role_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPermission_value() {
        return permission_value;
    }

    public void setPermission_value(String permission_value) {
        this.permission_value = permission_value;
    }

    public Integer getCreate_user_id() {
        return create_user_id;
    }

    public void setCreate_user_id(Integer create_user_id) {
        this.create_user_id = create_user_id;
    }

    public String getCreate_user_name() {
        return create_user_name;
    }

    public void setCreate_user_name(String create_user_name) {
        this.create_user_name = create_user_name;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

}


package com.hnwlxy.zr.EstateMS.common.dto;



import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class UserDTO implements Serializable {

    @ApiModelProperty(value = "序号")
    private int count;

    @ApiModelProperty(value = "所属角色")
    private String role_names;

    @ApiModelProperty(value = "用户账号")
    private String account;

    @ApiModelProperty(value = "用户姓名")
    private String name;


    @ApiModelProperty(value = "籍贯")
    private String address;

    @ApiModelProperty(value = "邮件")
    private String email;

    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "出生日期")
    private String birthday_str;

    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "性别,0男，1女")
    private String sex_str;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getRole_names() {
        return role_names;
    }

    public void setRole_names(String role_names) {
        this.role_names = role_names;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday_str() {
        return birthday_str;
    }

    public void setBirthday_str(String birthday_str) {
        this.birthday_str = birthday_str;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex_str() {
        return sex_str;
    }

    public void setSex_str(String sex_str) {
        this.sex_str = sex_str;
    }

}

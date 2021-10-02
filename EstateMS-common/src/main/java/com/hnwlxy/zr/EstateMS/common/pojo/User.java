package com.hnwlxy.zr.EstateMS.common.pojo;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    @ApiModelProperty(value = "用户id")
    private Integer user_id;

    @ApiModelProperty(value = "用户账号")
    private String user_account;

    @ApiModelProperty(value = "用户姓名")
    private String user_name;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "用户头像地址")
    private String user_head_url;

    @ApiModelProperty(value = "用户号码")
    private String user_phone;

    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "用户籍贯")
    private String user_address;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "用户出生日期")
    private Date birthday;

    @ApiModelProperty(value = "用户性别 0为男 1为女")
    private int gender;

    @ApiModelProperty(value = "是否已删除 0为保留1为删除")
    private int is_deleted;

    @ApiModelProperty(value = "乐观锁")
    private int version;

    @ApiModelProperty(value = "创建人id")
    private int create_user_id;

    @ApiModelProperty(value = "创建人名称")
    private String create_user_name;

    @ApiModelProperty(value = "创建时间")
    private Date create_time;

    @ApiModelProperty(value = "更新人用户id")
    private int update_user_id;

    @ApiModelProperty(value = "更新人用户名")
    private String update_user_name;

    @ApiModelProperty(value = "更新时间")
    private Date update_time;

    @ApiModelProperty(value = "会话sessionId")
    private String session_id;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_head_url() {
        return user_head_url;
    }

    public void setUser_head_url(String user_head_url) {
        this.user_head_url = user_head_url;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getCreate_user_id() {
        return create_user_id;
    }

    public void setCreate_user_id(int create_user_id) {
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

    public int getUpdate_user_id() {
        return update_user_id;
    }

    public void setUpdate_user_id(int update_user_id) {
        this.update_user_id = update_user_id;
    }

    public String getUpdate_user_name() {
        return update_user_name;
    }

    public void setUpdate_user_name(String update_user_name) {
        this.update_user_name = update_user_name;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("user_id=").append(user_id);
        sb.append(", user_account='").append(user_account).append('\'');
        sb.append(", user_name='").append(user_name).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", user_head_url='").append(user_head_url).append('\'');
        sb.append(", user_phone='").append(user_phone).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", user_address='").append(user_address).append('\'');
        sb.append(", birthday=").append(birthday);
        sb.append(", gender=").append(gender);
        sb.append(", is_deleted=").append(is_deleted);
        sb.append(", version='").append(version).append('\'');
        sb.append(", create_user_id=").append(create_user_id);
        sb.append(", create_user_name='").append(create_user_name).append('\'');
        sb.append(", create_time=").append(create_time);
        sb.append(", update_user_id=").append(update_user_id);
        sb.append(", update_user_name='").append(update_user_name).append('\'');
        sb.append(", update_time=").append(update_time);
        sb.append(", session_id='").append(session_id).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

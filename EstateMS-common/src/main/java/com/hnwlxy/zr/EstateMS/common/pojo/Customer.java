package com.hnwlxy.zr.EstateMS.common.pojo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class Customer implements Serializable {
    @ApiModelProperty(value = "客户id")
    private int customer_id;

    @ApiModelProperty(value = "客户身份证号")
    private String customer_card_id;

    @ApiModelProperty(value = "客户姓名")
    private String customer_name;

    @ApiModelProperty(value = "客户电话")
    private String customer_phone;

    @ApiModelProperty(value = "客户籍贯")
    private String customer_address;

    @ApiModelProperty(value = "客户性别 0为男 1为女")
    private int customer_sex;

    @ApiModelProperty(value = "创建人用户id")
    private int create_user_id;

    @ApiModelProperty(value = "创建人用户名")
    private String create_user_name;

    @ApiModelProperty(value = "创建时间")
    private Date create_time;

    @ApiModelProperty(value = "修改人用户id")
    private int update_user_id;

    @ApiModelProperty(value = "更新人用户名")
    private String update_user_name;

    @ApiModelProperty(value = "更新时间")
    private Date update_time;

    @ApiModelProperty(value = "是否删除 1删除0保留")
    private int is_deleted;

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public int getCustomer_sex() {
        return customer_sex;
    }

    public void setCustomer_sex(int customer_sex) {
        this.customer_sex = customer_sex;
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

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getCustomer_card_id() {
        return customer_card_id;
    }

    public void setCustomer_card_id(String customer_card_id) {
        this.customer_card_id = customer_card_id;
    }

    public int getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
    }
}

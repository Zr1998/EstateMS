package com.hnwlxy.zr.EstateMS.common.pojo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class Developer implements Serializable {
    @ApiModelProperty(value = "开发商编号")
    private int developer_id;

    @ApiModelProperty(value = "开发商名称")
    private String developer_name;

    @ApiModelProperty(value = "开发商电话号码")
    private String developer_telphone;

    @ApiModelProperty(value = "开发商地址")
    private String developer_address;

    @ApiModelProperty(value = "开发商法人")
    private String developer_legalPerson;

    @ApiModelProperty(value = "营业执照编号")
    private String developer_regNumber;

    public int getDeveloper_id() {
        return developer_id;
    }

    public void setDeveloper_id(int developer_id) {
        this.developer_id = developer_id;
    }

    public String getDeveloper_name() {
        return developer_name;
    }

    public void setDeveloper_name(String developer_name) {
        this.developer_name = developer_name;
    }

    public String getDeveloper_telphone() {
        return developer_telphone;
    }

    public void setDeveloper_telphone(String developer_telphone) {
        this.developer_telphone = developer_telphone;
    }

    public String getDeveloper_address() {
        return developer_address;
    }

    public void setDeveloper_address(String developer_address) {
        this.developer_address = developer_address;
    }

    public String getDeveloper_legalPerson() {
        return developer_legalPerson;
    }

    public void setDeveloper_legalPerson(String developer_legalPerson) {
        this.developer_legalPerson = developer_legalPerson;
    }

    public String getDeveloper_regNumber() {
        return developer_regNumber;
    }

    public void setDeveloper_regNumber(String developer_regNumber) {
        this.developer_regNumber = developer_regNumber;
    }
}

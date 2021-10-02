package com.hnwlxy.zr.EstateMS.common.pojo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class HouseType implements Serializable {
    @ApiModelProperty(value = "房型编号")
    private int hType_id;

    @ApiModelProperty(value = "房型名称")
    private String hType_name;

    @ApiModelProperty(value = "房型父节点id")
    private int hType_parentId;

    public int gethType_id() {
        return hType_id;
    }

    public void sethType_id(int hType_id) {
        this.hType_id = hType_id;
    }

    public String gethType_name() {
        return hType_name;
    }

    public void sethType_name(String hType_name) {
        this.hType_name = hType_name;
    }

    public int gethType_parentId() {
        return hType_parentId;
    }

    public void sethType_parentId(int hType_parentId) {
        this.hType_parentId = hType_parentId;
    }
}

package com.hnwlxy.zr.EstateMS.common.pojo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class ViewType implements Serializable {
    @ApiModelProperty(value = "朝向id")
    private int vType_id;
    @ApiModelProperty(value = "朝向名称")
    private int vType_name;

    public int getvType_id() {
        return vType_id;
    }

    public void setvType_id(int vType_id) {
        this.vType_id = vType_id;
    }

    public int getvType_name() {
        return vType_name;
    }

    public void setvType_name(int vType_name) {
        this.vType_name = vType_name;
    }
}

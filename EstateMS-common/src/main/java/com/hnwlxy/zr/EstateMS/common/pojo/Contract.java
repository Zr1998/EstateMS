package com.hnwlxy.zr.EstateMS.common.pojo;



import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class Contract implements Serializable {
    @ApiModelProperty(value = "合同id")
    private int contract_id;

    @ApiModelProperty(value = "开发商id")
    private int developer_id;

    @ApiModelProperty(value = "签约用户id")
    private int user_id;

    @ApiModelProperty(value = "签售房产id")
    private int estate_id;

    @ApiModelProperty(value = "签订合同时间")
    private Date create_time;

    public int getContract_id() {
        return contract_id;
    }

    public void setContract_id(int contract_id) {
        this.contract_id = contract_id;
    }

    public int getDeveloper_id() {
        return developer_id;
    }

    public void setDeveloper_id(int developer_id) {
        this.developer_id = developer_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getEstate_id() {
        return estate_id;
    }

    public void setEstate_id(int estate_id) {
        this.estate_id = estate_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}

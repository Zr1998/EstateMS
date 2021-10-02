package com.hnwlxy.zr.EstateMS.common.pojo;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class CustomerEstate implements Serializable {
    @ApiModelProperty(value = "客户房产自增id")
    private int customer_estate_id;

    @ApiModelProperty(value = "房产id")
    private int estate_id;

    @ApiModelProperty(value = "客户id")
    private int customer_id;

    @ApiModelProperty(value = "合同开发商id")
    private int developer_id;

    public int getCustomer_estate_id() {
        return customer_estate_id;
    }

    public void setCustomer_estate_id(int customer_estate_id) {
        this.customer_estate_id = customer_estate_id;
    }

    public int getEstate_id() {
        return estate_id;
    }

    public void setEstate_id(int estate_id) {
        this.estate_id = estate_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getDeveloper_id() {
        return developer_id;
    }

    public void setDeveloper_id(int developer_id) {
        this.developer_id = developer_id;
    }
}

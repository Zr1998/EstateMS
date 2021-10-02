package com.hnwlxy.zr.EstateMS.common.vo;



import com.hnwlxy.zr.EstateMS.common.pojo.Customer;
import com.hnwlxy.zr.EstateMS.common.pojo.CustomerEstate;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class CustomerVo {
    private int customer_id;

    private Customer customer;

    @ApiModelProperty("房产小区名称")
    private String estate_names;

    @ApiModelProperty("房产id")
    private int estate_id;

    private List<CustomerEstate> listCustomerEstate;

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getEstate_names() {
        return estate_names;
    }

    public void setEstate_names(String estate_names) {
        this.estate_names = estate_names;
    }

    public int getEstate_id() {
        return estate_id;
    }

    public void setEstate_id(int estate_id) {
        this.estate_id = estate_id;
    }

    public List<CustomerEstate> getListCustomerEstate() {
        return listCustomerEstate;
    }

    public void setListCustomerEstate(List<CustomerEstate> listCustomerEstate) {
        this.listCustomerEstate = listCustomerEstate;
    }
}

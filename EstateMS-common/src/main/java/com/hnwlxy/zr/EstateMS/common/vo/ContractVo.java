package com.hnwlxy.zr.EstateMS.common.vo;



import com.hnwlxy.zr.EstateMS.common.pojo.Contract;
import com.hnwlxy.zr.EstateMS.common.pojo.User;

import java.util.Date;
import java.util.List;

public class ContractVo {
        private int contract_id;

        private int estate_nums;

        private String developer_name;

        private Date sign_time;

        private String price;

        private List<Contract> contract;

        private User user;

        private String estate_names;

    public int getContract_id() {
        return contract_id;
    }

    public void setContract_id(int contract_id) {
        this.contract_id = contract_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getEstate_names() {
        return estate_names;
    }

    public void setEstate_names(String estate_names) {
        this.estate_names = estate_names;
    }

    public List<Contract> getContract() {
        return contract;
    }

    public void setContract(List<Contract> contract) {
        this.contract = contract;
    }

    public int getEstate_nums() {
        return estate_nums;
    }

    public void setEstate_nums(int estate_nums) {
        this.estate_nums = estate_nums;
    }

    public String getDeveloper_name() {
        return developer_name;
    }

    public void setDeveloper_name(String developer_name) {
        this.developer_name = developer_name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getSign_time() {
        return sign_time;
    }

    public void setSign_time(Date sign_time) {
        this.sign_time = sign_time;
    }
}

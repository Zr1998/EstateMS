package com.hnwlxy.zr.EstateMS.biz.service;


import com.hnwlxy.zr.EstateMS.common.model.BaseModel;
import com.hnwlxy.zr.EstateMS.common.vo.CustomerVo;

public interface CustomerService {
    void selectPageCustomerVo(BaseModel baseModel) throws Exception;

    void insertCustomer(CustomerVo customerVo, BaseModel baseModel)throws Exception;

    void updateCustomerVo(CustomerVo customerVo, BaseModel baseModel)throws Exception;

    void selectCustomerVoByCustomerId(BaseModel baseModel, int customerId)throws Exception;

    void deleteCustomerInPk(String customerIds, BaseModel baseModel) throws Exception;
}

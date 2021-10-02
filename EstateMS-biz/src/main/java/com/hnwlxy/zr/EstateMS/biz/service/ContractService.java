package com.hnwlxy.zr.EstateMS.biz.service;


import com.hnwlxy.zr.EstateMS.common.model.BaseModel;
import com.hnwlxy.zr.EstateMS.common.pojo.Contract;

public interface ContractService {
    void selectPageContractVo(BaseModel baseModel) throws Exception;

    void insertContract(Contract contract, BaseModel baseModel)throws Exception;

    void selectContractVoByUserId(int userId, BaseModel baseModel)throws Exception;
}

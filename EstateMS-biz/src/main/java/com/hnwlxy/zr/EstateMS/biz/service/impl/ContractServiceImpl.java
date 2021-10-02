package com.hnwlxy.zr.EstateMS.biz.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnwlxy.zr.EstateMS.common.exception.BusinessException;
import com.hnwlxy.zr.EstateMS.common.model.BaseModel;
import com.hnwlxy.zr.EstateMS.common.pojo.Contract;
import com.hnwlxy.zr.EstateMS.common.vo.ContractVo;
import lombok.extern.slf4j.Slf4j;
import com.hnwlxy.zr.EstateMS.biz.mapper.ContractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hnwlxy.zr.EstateMS.biz.service.ContractService;

import java.util.List;

@Service
@Slf4j
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractMapper contractMapper;

    public void selectPageContractVo(BaseModel baseModel) throws Exception {
        PageHelper.startPage(baseModel.getQueryParams().getCurr_page(), baseModel.getQueryParams().getPage_size());
        List<ContractVo> contractVo = contractMapper.selectContractVo(baseModel.getQueryParams());
        PageInfo contractlist = new PageInfo(contractVo);
        baseModel.setData(contractlist);
    }

    public void insertContract(Contract contract, BaseModel baseModel) throws Exception {
        //新增销售记录
        contractMapper.insertContract(contract);
    }

    public void selectContractVoByUserId(int userId, BaseModel baseModel) throws Exception {
        ContractVo contractVo=contractMapper.selectContractVoByUserId(userId);
        if (contractVo==null) {
            throw new BusinessException("该用户购房信息已被删除");
        }
        baseModel.setData(contractVo);
    }
}

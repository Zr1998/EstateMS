package com.hnwlxy.zr.EstateMS.biz.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnwlxy.zr.EstateMS.common.em.ResultMesgEnum;
import com.hnwlxy.zr.EstateMS.common.exception.BusinessException;
import com.hnwlxy.zr.EstateMS.common.model.BaseModel;
import com.hnwlxy.zr.EstateMS.common.pojo.Customer;
import com.hnwlxy.zr.EstateMS.common.pojo.CustomerEstate;
import com.hnwlxy.zr.EstateMS.common.vo.CustomerVo;
import lombok.extern.slf4j.Slf4j;
import com.hnwlxy.zr.EstateMS.biz.mapper.CustomerMapper;
import com.hnwlxy.zr.EstateMS.biz.mapper.EstateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hnwlxy.zr.EstateMS.biz.service.CustomerService;

import java.util.List;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private EstateMapper estateMapper;

    public void selectPageCustomerVo(BaseModel baseModel) throws Exception {
        PageHelper.startPage(baseModel.getQueryParams().getCurr_page(), baseModel.getQueryParams().getPage_size());
        List<CustomerVo> customerVo = customerMapper.selectCustomerVo(baseModel.getQueryParams());
        PageInfo customerlist = new PageInfo(customerVo);
        baseModel.setData(customerlist);
    }

    public void insertCustomer(CustomerVo customerVo, BaseModel baseModel) throws Exception {
        //验证客户身份证号
        Customer oldCustomer=customerMapper.selectCustomerByCardId(customerVo.getCustomer().getCustomer_card_id());
        if(oldCustomer!=null){
            throw new BusinessException("客户已存在，请勿重复添加");
        }
        //新增客户
        customerMapper.insertCustomer(customerVo.getCustomer());
        //新增客户房产关系
        if(customerVo.getListCustomerEstate()!=null&&customerVo.getListCustomerEstate().size()>0){
            //存在客户房产关系
            for(int i=0;i<customerVo.getListCustomerEstate().size();i++){
                CustomerEstate customerEstate=customerVo.getListCustomerEstate().get(i);
                customerEstate.setCustomer_id(customerVo.getCustomer().getCustomer_id());
                customerMapper.insertCustomerEstate(customerEstate);
            }
        }
        //修改房产销售状态
        int num=estateMapper.tiggerStateByCustomerId(customerVo.getCustomer().getCustomer_id());
        if(num == 0){
            throw new BusinessException(ResultMesgEnum.UPDATE);
        }
    }

    public void updateCustomerVo(CustomerVo customerVo, BaseModel baseModel) throws Exception {
        //验证客户身份证号
        Customer oldCustomer=customerMapper.selectCustomerByCardId(customerVo.getCustomer().getCustomer_card_id());
        if(oldCustomer!=null&&oldCustomer.getCustomer_id()!=customerVo.getCustomer().getCustomer_id()){//说明客户存在客户信息并且id不相等
            throw new BusinessException("'该身份证号为:'"+oldCustomer.getCustomer_card_id()+"的客户已存在，请勿重复添加");
        }
        //修改客户
        int count = customerMapper.updateCustomerVo(customerVo.getCustomer());
        if(count == 0){
            throw new BusinessException(ResultMesgEnum.UPDATE_VES);
        }
        customerMapper.deleteEstateNames(customerVo.getCustomer().getCustomer_id() + "");
        //新增客户房产关系
        if(customerVo.getListCustomerEstate()!=null&&customerVo.getListCustomerEstate().size()>0){
            //存在客户房产关系
            for(int i=0;i<customerVo.getListCustomerEstate().size();i++){
                CustomerEstate customerEstate=customerVo.getListCustomerEstate().get(i);
                customerEstate.setCustomer_id(customerVo.getCustomer().getCustomer_id());
                customerMapper.insertCustomerEstate(customerEstate);
            }
        }
        //修改房产销售状态
        int num=estateMapper.tiggerStateByCustomerId(customerVo.getCustomer().getCustomer_id());
        if(num == 0){
            throw new BusinessException(ResultMesgEnum.UPDATE);
        }
    }

    public void selectCustomerVoByCustomerId(BaseModel baseModel, int customerId) throws Exception {
        CustomerVo customerVo=customerMapper.selectCustomerVoByCustomerId(customerId);
        if (customerVo==null) {
            throw new BusinessException("该客户信息已被删除");
        }
        baseModel.setData(customerVo);
    }

    /*
     * @title:<h3> 理论删除客户 <h3>
     * @author: Zr
     * @date:  2021/2/17  12:14
     * @params [customerIds, baseModel]
     * @return void
     **/
    public void deleteCustomerInPk(String customerIds, BaseModel baseModel) throws Exception {
        //删除客户房产信息表记录
        customerMapper.deleteEstateNames(customerIds);
        estateMapper.changeStateByCustomerId(customerIds);
        //删除客户信息
        int count=customerMapper.removeCustomerInPk(customerIds);
        baseModel.setMessage("删除"+count+"条客户信息成功");
    }


}

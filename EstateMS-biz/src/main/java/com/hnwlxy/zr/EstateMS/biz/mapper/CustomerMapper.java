package com.hnwlxy.zr.EstateMS.biz.mapper;


import com.hnwlxy.zr.EstateMS.common.model.QueryParams;
import com.hnwlxy.zr.EstateMS.common.pojo.Customer;
import com.hnwlxy.zr.EstateMS.common.pojo.CustomerEstate;
import com.hnwlxy.zr.EstateMS.common.vo.CustomerVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerMapper {
    //分页查询客户列表
    List<CustomerVo> selectCustomerVo(QueryParams queryParams);

    Customer selectCustomerByCardId(@Param("cardId") String cardId);

    CustomerVo selectCustomerVoByCustomerId(@Param("customerId") int customerId);

    int insertCustomer(Customer customer);

    int updateCustomerVo(Customer customer);

    int removeCustomerInPk(@Param("customerIds") String customerIds);

    int insertCustomerEstate(CustomerEstate customerEstate);

    int deleteEstateNames(@Param("fkCustomerIds") String fkCustomerIds);

    CustomerVo selectEstateName();
}

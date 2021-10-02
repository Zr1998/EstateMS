package com.hnwlxy.zr.EstateMS.biz.mapper;


import com.hnwlxy.zr.EstateMS.common.model.QueryParams;
import com.hnwlxy.zr.EstateMS.common.pojo.Contract;
import com.hnwlxy.zr.EstateMS.common.vo.ContractVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ContractMapper {
    List<ContractVo> selectContractVo(QueryParams queryParams);

    ContractVo selectContractVoByUserId(@Param("userId") int userId);

    int insertContract(Contract contract);
}

package com.hnwlxy.zr.EstateMS.biz.mapper;



import com.hnwlxy.zr.EstateMS.common.model.QueryParams;
import com.hnwlxy.zr.EstateMS.common.pojo.OperationLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OperationLogMapper {
    List<OperationLog> selectPageOperationLog(QueryParams queryParams) throws Exception;
    int insert(OperationLog operationLog);
}

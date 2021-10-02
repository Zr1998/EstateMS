package com.hnwlxy.zr.EstateMS.biz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnwlxy.zr.EstateMS.common.model.BaseModel;
import com.hnwlxy.zr.EstateMS.common.pojo.OperationLog;
import lombok.extern.slf4j.Slf4j;
import com.hnwlxy.zr.EstateMS.biz.mapper.OperationLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hnwlxy.zr.EstateMS.biz.service.OperationService;

import java.util.List;

@Service
@Slf4j
public class OperationServiceImpl implements OperationService {
    @Autowired
    private OperationLogMapper operationLogMapper;

    /*
     * @title:<h3> 分页查询操作日志列表 <h3>
     * @author: Zr
     * @date: 2021/1/17  15:33
     * @params [baseModel]
     * @return void
     **/
    public void selectPageOperationLog(BaseModel baseModel) throws Exception {
        PageHelper.startPage(baseModel.getQueryParams().getCurr_page(),baseModel.getQueryParams().getPage_size());
        List<OperationLog> ops = operationLogMapper.selectPageOperationLog(baseModel.getQueryParams());
        PageInfo opList = new PageInfo(ops);
        baseModel.setData(opList);
    }
}

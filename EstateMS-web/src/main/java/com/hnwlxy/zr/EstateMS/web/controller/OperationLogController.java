package com.hnwlxy.zr.EstateMS.web.controller;

import com.hnwlxy.zr.EstateMS.common.contants.BaseContants;
import com.hnwlxy.zr.EstateMS.common.em.RoleMenuEnum;
import com.hnwlxy.zr.EstateMS.web.aop.AopOperation;
import com.hnwlxy.zr.EstateMS.web.baseController.BaseController;
import com.hnwlxy.zr.EstateMS.common.model.BaseModel;

import com.hnwlxy.zr.EstateMS.common.pojo.OperationLog;
import com.hnwlxy.zr.EstateMS.biz.service.OperationService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("operationLog")
public class OperationLogController extends BaseController {
    @Autowired
    private OperationService operationService;

   @AopOperation(menu = RoleMenuEnum.NO_101,type = BaseContants.OPERATION_TYPE.SEARCH)
    @ApiOperation(value = "分页查询登录日志",notes = "分页查询登录日志",httpMethod = "POST",response = OperationLog.class)
    @RequestMapping("selectPageOperationLog")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "baseModel.queryParams.curr_page",value ="当前页码",dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "baseModel.queryParams.page_size",value ="每页显示的记录数",dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "baseModel.queryParams.where",value ="查询条件",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "baseModel.queryParams.order",value ="排序",dataType = "String",paramType = "query")})
    public BaseModel selectPageOperationLog(@ModelAttribute("baseModel")BaseModel baseModel)throws Exception{
        operationService.selectPageOperationLog(baseModel);
        baseModel.setMessage("分页查询成功");
        return baseModel;
    }
}

package com.hnwlxy.zr.EstateMS.web.controller;


import com.hnwlxy.zr.EstateMS.common.contants.BaseContants;
import com.hnwlxy.zr.EstateMS.web.aop.AopOperation;
import com.hnwlxy.zr.EstateMS.web.baseController.BaseController;
import com.hnwlxy.zr.EstateMS.common.em.RoleMenuEnum;
import com.hnwlxy.zr.EstateMS.common.exception.BusinessException;
import com.hnwlxy.zr.EstateMS.common.model.BaseModel;
import com.hnwlxy.zr.EstateMS.common.pojo.Contract;
import com.hnwlxy.zr.EstateMS.biz.service.ContractService;
import com.hnwlxy.zr.EstateMS.common.vo.ContractVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("contract")
public class ContractController extends BaseController {

    @Autowired
    private ContractService contractService;

    @RequestMapping(value = "/selectPageContractVo",method = RequestMethod.POST)
    @ResponseBody
    @AopOperation(menu = RoleMenuEnum.No_303,type = BaseContants.OPERATION_TYPE.SEARCH)
    @ApiOperation(value = "分页查询销售信息列表",notes = "查询",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "baseModel.queryParams.curr_page",value ="当前页码",dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "baseModel.queryParams.page_size",value ="每页显示的记录数",dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "baseModel.queryParams.where",value ="查询条件",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "baseModel.queryParams.order",value ="排序",dataType = "String",paramType = "query")})
    public BaseModel selectPageContractVo(@ModelAttribute("baseModel")BaseModel baseModel) throws Exception{
        contractService.selectPageContractVo(baseModel);
        baseModel.setMessage("销售信息列表查询成功");
        return baseModel;
    }

    @ApiOperation(value = "新增销售记录",httpMethod = "POST",response = Contract.class)
    @RequestMapping("insertContract")
    public BaseModel insertContract(BaseModel baseModel, @ModelAttribute("") Contract contract)throws Exception{
        if(contract==null){
            throw  new BusinessException("销售信息不能为空");
        }else if(contract.getEstate_id()==0 || "".equals(contract.getEstate_id())){
            throw  new BusinessException("所购房不能为空");
        }else if(contract.getUser_id()==0 || "".equals(contract.getUser_id())){
            throw  new BusinessException("购房者不能为空");
        }
        contractService.insertContract(contract,baseModel);
        baseModel.setTempMFile(null);
        baseModel.setMessage("销售记录新增成功");
        return baseModel;
    }

    @ApiOperation(value = "根据用户id查询购房详情", httpMethod = "GET",response = ContractVo.class)
    @AopOperation(menu = RoleMenuEnum.No_303,type = BaseContants.OPERATION_TYPE.SEARCH)
    @RequestMapping("/selectContractVoByUserId/{userId}")
    public BaseModel selectContractVoByUserId(BaseModel  baseModel,@PathVariable("userId") int userId) throws Exception{
        contractService.selectContractVoByUserId(userId,baseModel);
        baseModel.setMessage("根据用户id查询购房详情成功");
        return baseModel;
    }
}

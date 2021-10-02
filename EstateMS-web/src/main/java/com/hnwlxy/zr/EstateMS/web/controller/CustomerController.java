package com.hnwlxy.zr.EstateMS.web.controller;


import com.hnwlxy.zr.EstateMS.common.contants.BaseContants;
import com.hnwlxy.zr.EstateMS.common.em.RoleMenuEnum;
import com.hnwlxy.zr.EstateMS.web.aop.AopOperation;
import com.hnwlxy.zr.EstateMS.web.baseController.BaseController;
import com.hnwlxy.zr.EstateMS.common.exception.BusinessException;
import com.hnwlxy.zr.EstateMS.common.model.BaseModel;
import com.hnwlxy.zr.EstateMS.biz.service.CustomerService;
import com.hnwlxy.zr.EstateMS.common.vo.CustomerVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("customer")
public class CustomerController extends BaseController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/selectPageCustomerVo",method = RequestMethod.POST)
    @ResponseBody
    @AopOperation(menu = RoleMenuEnum.No_302,type = BaseContants.OPERATION_TYPE.SEARCH)
    @ApiOperation(value = "分页查询客户列表",notes = "查询",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "baseModel.queryParams.curr_page",value ="当前页码",dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "baseModel.queryParams.page_size",value ="每页显示的记录数",dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "baseModel.queryParams.where",value ="查询条件",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "baseModel.queryParams.order",value ="排序",dataType = "String",paramType = "query")})
    public BaseModel selectPageCustomer(@ModelAttribute("baseModel")BaseModel baseModel) throws Exception{
        customerService.selectPageCustomerVo(baseModel);
        baseModel.setMessage("客户列表查询成功");
        return baseModel;
    }

    @AopOperation(menu = RoleMenuEnum.No_302,type = BaseContants.OPERATION_TYPE.ADD)
    @ApiOperation(value = "新增客户信息",httpMethod = "POST",response = CustomerVo.class)
    @RequestMapping("insertCustomerVo")
    public BaseModel insertCustomerVo(BaseModel baseModel, @ModelAttribute("") CustomerVo customerVo)throws Exception{
        if(customerVo==null){
            throw  new BusinessException("客户信息不能为空");
        }else if(customerVo.getCustomer().getCustomer_name()==null || "".equals(customerVo.getCustomer().getCustomer_name())){
            throw  new BusinessException("客户姓名不能为空");
        }else if(customerVo.getCustomer().getCustomer_card_id()==null || "".equals(customerVo.getCustomer().getCustomer_card_id())){
            throw  new BusinessException("客户身份证号码不能为空");
        } else if(customerVo.getCustomer().getCustomer_phone()==null || "".equals(customerVo.getCustomer().getCustomer_phone())){
            throw  new BusinessException("客户电话号码不能为空");
        }
        customerVo.getCustomer().setCreate_user_id(getSessionUser().getUser_id());
        customerVo.getCustomer().setCreate_user_name(getSessionUser().getUser().getUser_name());
        customerService.insertCustomer(customerVo,baseModel);
        baseModel.setTempMFile(null);
        baseModel.setMessage("客户新增成功");
        return baseModel;
    }

    @AopOperation(menu = RoleMenuEnum.No_302,type = BaseContants.OPERATION_TYPE.SEARCH)
    @ApiOperation(value = "根据客户id查询客户详情", httpMethod = "GET",response = CustomerVo.class)
    @RequestMapping("/selectCustomerVoByCustomerId/{customerId}")
    public BaseModel selectCustomerVoByCustomerId(BaseModel  baseModel,@PathVariable("customerId") int customerId) throws Exception{
        customerService.selectCustomerVoByCustomerId(baseModel,customerId);
        baseModel.setMessage("根据客户id查询客户详情成功");
        return baseModel;
    }

    @AopOperation(menu = RoleMenuEnum.No_302,type = BaseContants.OPERATION_TYPE.UPDATE)
    @ApiOperation(value = "修改客户信息",notes = "修改客户信息",httpMethod = "POST",response = CustomerVo.class)
    @RequestMapping(value="updateCustomerVo",method =RequestMethod.POST)
    public BaseModel updateCustomerVo(CustomerVo customerVo,BaseModel baseModel) throws Exception{
        if(customerVo == null || customerVo.getCustomer()==null) {
            throw new BusinessException("客户信息不能为空");
        }else if(customerVo.getCustomer().getCustomer_id()==0){
            throw new BusinessException("客户id不能为空");
        }else if(customerVo.getCustomer().getCustomer_card_id() == null || "".equals(customerVo.getCustomer().getCustomer_card_id())){
            throw new BusinessException("客户身份证号不能为空");
        }else if(customerVo.getCustomer().getCustomer_name()==null || "".equals(customerVo.getCustomer().getCustomer_name())){
            throw  new BusinessException("客户姓名不能为空");
        }
        customerVo.getCustomer().setUpdate_user_id(getSessionUser().getUser_id());
        customerVo.getCustomer().setUpdate_user_name(getSessionUser().getUser().getUser_name());
        customerService.updateCustomerVo(customerVo,baseModel);
        baseModel.setMessage("修改客户信息成功");
        baseModel.setTempMFile(null);
        baseModel.setAop_mesg("客户id:"+customerVo.getCustomer().getCustomer_id()+"，姓名："+customerVo.getCustomer().getCustomer_name());
        return baseModel;
    }

    @AopOperation(menu = RoleMenuEnum.No_302,type = BaseContants.OPERATION_TYPE.DELETE)
    @ApiOperation(value = "删除客户信息",httpMethod = "GET")
    @RequestMapping("deleteCustomer/{ids}")
    public BaseModel deleteCustomerById(BaseModel baseModel,@PathVariable("ids") String customerIds) throws Exception {
        getSessionUser();
        customerService.deleteCustomerInPk(customerIds,baseModel);
        baseModel.setAop_mesg(customerIds);
        return baseModel;
    }


}

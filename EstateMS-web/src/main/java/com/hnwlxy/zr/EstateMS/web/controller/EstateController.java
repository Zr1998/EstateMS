package com.hnwlxy.zr.EstateMS.web.controller;


import com.hnwlxy.zr.EstateMS.common.contants.BaseContants;
import com.hnwlxy.zr.EstateMS.common.em.RoleMenuEnum;
import com.hnwlxy.zr.EstateMS.web.aop.AopOperation;
import com.hnwlxy.zr.EstateMS.web.baseController.BaseController;
import com.hnwlxy.zr.EstateMS.common.exception.BusinessException;
import com.hnwlxy.zr.EstateMS.common.model.BaseModel;
import com.hnwlxy.zr.EstateMS.common.pojo.Estate;
import com.hnwlxy.zr.EstateMS.biz.service.EstateService;
import com.hnwlxy.zr.EstateMS.common.vo.EstateVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("estate")
public class EstateController extends BaseController {
    @Autowired
    private EstateService estateService;


    @RequestMapping(value = "/selectPageEstates",method = RequestMethod.POST)
    @ResponseBody
    @AopOperation(menu = RoleMenuEnum.No_301,type = BaseContants.OPERATION_TYPE.SEARCH)
    @ApiOperation(value = "分页查询房产列表",notes = "查询",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "baseModel.queryParams.curr_page",value ="当前页码",dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "baseModel.queryParams.page_size",value ="每页显示的记录数",dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "baseModel.queryParams.where",value ="查询条件",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "baseModel.queryParams.order",value ="排序",dataType = "String",paramType = "query")})
    public BaseModel login(@ModelAttribute("baseModel")BaseModel baseModel) throws Exception{
        estateService.selectPageEstateVo(baseModel);
        baseModel.setMessage("房产列表查询成功");
        return baseModel;
    }


    @AopOperation(menu = RoleMenuEnum.No_301,type = BaseContants.OPERATION_TYPE.ADD)
    @ApiOperation(value = "新增房屋信息",httpMethod = "POST",response = EstateVo.class)
    @RequestMapping("insertEstateVo")
    public BaseModel insertEstateVo(BaseModel baseModel,@ModelAttribute("") EstateVo estateVo)throws Exception{
        if(estateVo==null){
            throw  new BusinessException("房屋信息不能为空");
        }else if(estateVo.getEstate().getEstate_name()==null || "".equals(estateVo.getEstate().getEstate_name())){
            throw  new BusinessException("小区名称不能为空");
        }else if(estateVo.getEstate().getEstate_area()==null || "".equals(estateVo.getEstate().getEstate_area())){
            throw  new BusinessException("房屋面积不能为空");
        }else if(estateVo.getEstate().getEstate_price()==null || "".equals(estateVo.getEstate().getEstate_price())){
            throw  new BusinessException("房屋价格不能为空");
        }else if(estateVo.getEstate().getEstate_buildingNum()==null || "".equals(estateVo.getEstate().getEstate_buildingNum())){
            throw  new BusinessException("房屋楼号不能为空");
        }else if(estateVo.getEstate().getEstate_unitNum()==null || "".equals(estateVo.getEstate().getEstate_unitNum())){
            throw  new BusinessException("房屋单元号不能为空");
        }else if(estateVo.getEstate().getEstate_floorNum()==null || "".equals(estateVo.getEstate().getEstate_floorNum())){
            throw  new BusinessException("房屋楼层号不能为空");
        }else if(estateVo.getEstate().getEstate_structure()==null || "".equals(estateVo.getEstate().getEstate_structure())){
            throw  new BusinessException("房屋格局不能为空");
        }else if(estateVo.getEstate().getEstate_roomNum()==null || "".equals(estateVo.getEstate().getEstate_roomNum())){
            throw  new BusinessException("房间号不能为空");
        }

        estateVo.getEstate().setCreate_user_id(getSessionUser().getUser_id());
        estateVo.getEstate().setCreate_user_name(getSessionUser().getUser().getUser_name());
        estateService.insertEstateVo(estateVo,baseModel);
        baseModel.setMessage("房屋新增成功");
        baseModel.setTempMFile(null);
        return baseModel;
    }

    @AopOperation(menu = RoleMenuEnum.No_301,type = BaseContants.OPERATION_TYPE.SEARCH)
    @ApiOperation(value = "根据房产id查询房产详情", httpMethod = "GET",response = EstateVo.class)
    @RequestMapping("/selectEstateVoByEstateId/{estateId}")
    public BaseModel selectEstateVoByEstateId(BaseModel  baseModel,@PathVariable("estateId") int estateId) throws Exception{
        estateService.selectEstateVoByEstateId(baseModel,estateId);
        baseModel.setMessage("根据房屋id查询房屋详情成功");
        return baseModel;
    }

    @AopOperation(menu = RoleMenuEnum.No_301,type = BaseContants.OPERATION_TYPE.UPDATE)
    @ApiOperation(value = "修改房屋信息",notes = "修改房屋信息",httpMethod = "POST",response = EstateVo.class)
    @RequestMapping(value="updateEstateVo",method =RequestMethod.POST)
    public BaseModel updateEstateVo(EstateVo estateVo,BaseModel baseModel) throws Exception{
        if(estateVo == null || estateVo.getEstate()==null) {
            throw new BusinessException("房屋信息不能为空");
        }else if(estateVo.getEstate().getEstate_name()==null || "".equals(estateVo.getEstate().getEstate_name())){
            throw  new BusinessException("小区名称不能为空");
        }else if(estateVo.getEstate().getEstate_area()==null || "".equals(estateVo.getEstate().getEstate_area())){
            throw  new BusinessException("房屋面积不能为空");
        }else if(estateVo.getEstate().getEstate_price()==null || "".equals(estateVo.getEstate().getEstate_price())){
            throw  new BusinessException("房屋价格不能为空");
        }else if(estateVo.getEstate().getEstate_buildingNum()==null || "".equals(estateVo.getEstate().getEstate_buildingNum())){
            throw  new BusinessException("房屋楼号不能为空");
        }else if(estateVo.getEstate().getEstate_unitNum()==null || "".equals(estateVo.getEstate().getEstate_unitNum())){
            throw  new BusinessException("房屋单元号不能为空");
        }else if(estateVo.getEstate().getEstate_floorNum()==null || "".equals(estateVo.getEstate().getEstate_floorNum())){
            throw  new BusinessException("房屋楼层号不能为空");
        }else if(estateVo.getEstate().getEstate_structure()==null || "".equals(estateVo.getEstate().getEstate_structure())){
            throw  new BusinessException("房屋格局不能为空");
        }else if(estateVo.getEstate().getEstate_roomNum()==null || "".equals(estateVo.getEstate().getEstate_roomNum())){
            throw  new BusinessException("房间号不能为空");
        }
        estateVo.getEstate().setUpdate_user_id(getSessionUser().getUser_id());
        estateVo.getEstate().setUpdate_user_name(getSessionUser().getUser().getUser_name());
        estateService.updateEstateVo(estateVo,baseModel);
        baseModel.setMessage("修改房屋信息成功");
        baseModel.setTempMFile(null);
        baseModel.setAop_mesg("房屋id:"+estateVo.getEstate().getEstate_id()+"，小区："+estateVo.getEstate().getEstate_name());
        return baseModel;
    }

    @AopOperation(menu = RoleMenuEnum.No_301,type = BaseContants.OPERATION_TYPE.DELETE)
    @ApiOperation(value = "删除房屋信息",httpMethod = "GET")
    @RequestMapping("deleteEstate/{ids}")
    public BaseModel deleteEstateById(BaseModel baseModel,@PathVariable("ids") String estateIds) throws Exception {
        getSessionUser();
        estateService.deleteEstateInPk(estateIds,baseModel);
        baseModel.setAop_mesg(estateIds);
        return baseModel;
    }

    @AopOperation(menu = RoleMenuEnum.No_301,type = BaseContants.OPERATION_TYPE.UPDATE)
    @ApiOperation(value = "切换房屋销售状态",httpMethod = "GET")
    @RequestMapping("tiggerState/{estateId}")
    public BaseModel tiggerState(@PathVariable("estateId") int estateId, BaseModel baseModel) throws Exception{
        //1.得到房屋信息
        Estate estate = new Estate();
        estate.setEstate_id(estateId);
        //2.修改房屋销售状态
        estateService.tiggerState(estate,baseModel);
        baseModel.setMessage("房产销售状态切换成功");
        baseModel.setAop_mesg(estateId+"");
        return baseModel;
    }

    @ApiOperation(value = "查询房产名称",httpMethod = "GET",response = Estate.class)
    @RequestMapping("selectEstateName")
    public BaseModel selectEstateName(BaseModel baseModel) throws Exception{
        estateService.selectEstateName(baseModel);
        baseModel.setMessage("查询房屋名称成功");
        return  baseModel;
    }

    @ApiOperation(value = "查询房产开发商",httpMethod = "GET",response = Estate.class)
    @RequestMapping("selectDeveloperName")
    public BaseModel selectDeveloperName(BaseModel baseModel) throws Exception{
        estateService.selectDeveloperName(baseModel);
        baseModel.setMessage("查询房产开发商成功");
        return  baseModel;
    }
}

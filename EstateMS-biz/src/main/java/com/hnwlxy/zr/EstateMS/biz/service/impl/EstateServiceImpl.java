package com.hnwlxy.zr.EstateMS.biz.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnwlxy.zr.EstateMS.common.em.ResultMesgEnum;
import com.hnwlxy.zr.EstateMS.common.exception.BusinessException;
import com.hnwlxy.zr.EstateMS.common.model.BaseModel;
import com.hnwlxy.zr.EstateMS.common.pojo.Estate;
import com.hnwlxy.zr.EstateMS.common.vo.EstateVo;
import lombok.extern.slf4j.Slf4j;
import com.hnwlxy.zr.EstateMS.biz.mapper.EstateMapper;
import com.hnwlxy.zr.EstateMS.biz.mapper.FileLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hnwlxy.zr.EstateMS.biz.service.EstateService;
import com.hnwlxy.zr.EstateMS.biz.service.FileLogService;

import java.util.List;

@Service
@Slf4j
public class EstateServiceImpl implements EstateService {
    @Autowired
     private EstateMapper estateMapper;
    @Autowired
    private FileLogMapper fileLogMapper;
    @Autowired
    private FileLogService fileLogService;

    /*
     * @title:<h3> 分页查询房屋列表 <h3>
     * @author: Zr
     * @date:  2021/2/10  15:36
     * @params [baseModel]
     * @return void
     **/
    public void selectPageEstateVo(BaseModel baseModel) throws Exception {
        PageHelper.startPage(baseModel.getQueryParams().getCurr_page(), baseModel.getQueryParams().getPage_size());
        List<EstateVo> estateVo = estateMapper.selectEstateVo(baseModel.getQueryParams());
        PageInfo estateliset = new PageInfo(estateVo);
        baseModel.setData(estateliset);
    }

    /*
     * @title:<h3> 根据房屋id查询详情 <h3>
     * @author: Zr
     * @date:  2021/2/10  15:36
     * @params [baseModel, estateId]
     * @return void
     **/
    public void selectEstateVoByEstateId(BaseModel baseModel, int estateId) throws Exception {
        EstateVo estateVo=estateMapper.selectEstateVoByEstateId(estateId);
        if (estateVo==null) {
            throw new BusinessException("该房屋信息已被删除");
        }
        baseModel.setData(estateVo);
    }

    /*
     * @title:<h3> 切换房产销售状态 <h3>
     * @author: Zr
     * @date:  2021/2/10   16:58
     * @params [estate, baseModel]
     * @return void
     **/
    public void tiggerState(Estate estate, BaseModel baseModel) throws Exception {
        //1.根据ID获取房屋信息
        Estate oldEstate = estateMapper.selectEstateByEstateId(estate.getEstate_id());
        if(oldEstate!=null){
            if(oldEstate.getEstate_state()==1){
                estate.setEstate_state(0);
            }else if(oldEstate.getEstate_state()==0){
                estate.setEstate_state(1);
            }
        }else {
            throw  new BusinessException("修改的房屋信息不存在");
        }
        int count= estateMapper.tiggerState(estate);
        if(count==0){
            throw new BusinessException("切换状态失败");
        }
    }

    /*
     * @title:<h3> 理论删除房屋 <h3>
     * @author: Zr
     * @date:  2021/2/10   16:58
     * @params [estateIds, baseModel]
     * @return void
     **/
    public void deleteEstateInPk(String estateIds, BaseModel baseModel) throws Exception {
        //记录删除房屋信息记录
        int count=estateMapper.deleteEstate(estateIds);
        baseModel.setMessage("删除"+count+"条房屋信息成功");
    }

    /*
     * @title:<h3> 新增房屋信息 <h3>
     * @author: Zr
     * @date: 2021/2/10   17:15
     * @params [estateVo, baseModel]
     * @return void
     **/
    public void insertEstateVo(EstateVo estateVo, BaseModel baseModel) throws Exception {
        //验证房屋是否存在
        Estate oldEstate=estateMapper.selectEstateByEstateId(estateVo.getEstate().getEstate_id());
        if(oldEstate!=null){
            throw new BusinessException("该房屋已存在，请勿重复添加");
        }
        //判断是否进行文件上传
        fileLogService.uploadFiles(baseModel,estateVo.getEstate().getCreate_user_name());
        estateVo.getEstate().setEstate_photo(baseModel.getFilesArray());
        //新增房屋
        estateMapper.insertEstate(estateVo.getEstate());
        baseModel.setTempMFile(baseModel.getTempMFile());
        baseModel.setMessage("新增房屋信息成功");
    }

    /*
     * @title:<h3> 修改房屋信息 <h3>
     * @author: Zr
     * @date:  2021/2/10   18:06
     * @params [estateVo, baseModel]
     * @return void
     **/
    public void updateEstateVo(EstateVo estateVo, BaseModel baseModel) throws Exception {
        Estate oldEstate=estateMapper.selectEstateByEstateId(estateVo.getEstate().getEstate_id());
        if(oldEstate!=null&&oldEstate.getEstate_id()!=estateVo.getEstate().getEstate_id()){ //说明房屋存在房屋信息并且id不相等
            throw new BusinessException("该 '" + estateVo.getEstate().getEstate_name() + "'小区已存在'"+
                    estateVo.getEstate().getEstate_buildingNum()+estateVo.getEstate().getEstate_unitNum()+
                    estateVo.getEstate().getEstate_floorNum()+estateVo.getEstate().getEstate_roomNum()+"'室房屋，请勿重复添加");
        }
        //判断是否进行文件上传
        fileLogService.uploadFiles(baseModel,estateVo.getEstate().getUpdate_user_name());
        if(baseModel.getFilesArray()!=null){//如果存在图像更新，则更新图像id
            estateVo.getEstate().setEstate_photo(baseModel.getFilesArray());
            //删除文件上传记录表中的记录
            fileLogMapper.removeFileLogByEstateIds(estateVo.getEstate().getEstate_id() + "");
        }
        int count = estateMapper.updateEstateVo(estateVo.getEstate());
        if(count == 0){
            throw new BusinessException(ResultMesgEnum.UPDATE_VES);
        }

        baseModel.setMessage("修改房屋信息成功");
    }

    public void selectEstateName(BaseModel baseModel) throws Exception{
        baseModel.setData(estateMapper.selectEstateName());
    }

    public void selectDeveloperName(BaseModel baseModel) throws Exception {
        baseModel.setData(estateMapper.selectEstateDeveloper());
    }
}

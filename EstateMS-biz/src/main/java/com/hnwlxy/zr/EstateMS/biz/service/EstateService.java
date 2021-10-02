package com.hnwlxy.zr.EstateMS.biz.service;


import com.hnwlxy.zr.EstateMS.common.model.BaseModel;
import com.hnwlxy.zr.EstateMS.common.pojo.Estate;
import com.hnwlxy.zr.EstateMS.common.vo.EstateVo;

public interface EstateService {
    void selectPageEstateVo(BaseModel baseModel)  throws Exception;

    void selectEstateVoByEstateId(BaseModel baseModel, int estateId) throws Exception;

    void deleteEstateInPk(String estateIds, BaseModel baseModel)throws Exception;

    void tiggerState(Estate estate, BaseModel baseModel)throws Exception;

    void insertEstateVo(EstateVo estateVo, BaseModel baseModel)throws Exception;

    void updateEstateVo(EstateVo estateVo, BaseModel baseModel)throws Exception;

    void selectEstateName(BaseModel baseModel)throws Exception;

    void selectDeveloperName(BaseModel baseModel) throws Exception;
}

package com.hnwlxy.zr.EstateMS.biz.service;

import com.hnwlxy.zr.EstateMS.common.model.BaseModel;
import com.hnwlxy.zr.EstateMS.common.vo.SysUserVo;

public interface LoginService {
    void login(SysUserVo userVo, BaseModel baseModel) throws Exception;

    void selectEnumRoleMenu(BaseModel baseModel) throws  Exception;

   //void loginOut(SysUserVo userVo) throws Exception;
}

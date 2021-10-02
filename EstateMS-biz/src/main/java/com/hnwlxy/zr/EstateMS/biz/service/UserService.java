package com.hnwlxy.zr.EstateMS.biz.service;

import com.hnwlxy.zr.EstateMS.common.model.BaseModel;
import com.hnwlxy.zr.EstateMS.common.pojo.User;
import com.hnwlxy.zr.EstateMS.common.vo.SysUserVo;

public interface UserService {
    void selectUserVoByUserId(BaseModel baseModel, int userId) throws Exception;

    void registUserVo(BaseModel baseModel, SysUserVo userVo) throws Exception;

    void selectPageUserVo(BaseModel baseModel)throws Exception;

    void inserUserVo(SysUserVo userVo, BaseModel baseModel)throws Exception;

    void udateUserVo(SysUserVo userVo, BaseModel baseModel)throws Exception;

    void deleteUserInPk(String userIds, BaseModel baseModel)throws Exception;

    void updatePassword(SysUserVo userVo, BaseModel baseModel) throws Exception;

    void resetPassword(User user, BaseModel baseModel)throws Exception;

    void updateMyUserVo(SysUserVo userVo, BaseModel baseModel) throws Exception;

    String exportUserDTO(BaseModel baseModel) throws Exception;
    void impUserDTO(User user, BaseModel baseModel)throws Exception;
}

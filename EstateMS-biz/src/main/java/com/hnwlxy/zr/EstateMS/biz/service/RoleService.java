package com.hnwlxy.zr.EstateMS.biz.service;


import com.hnwlxy.zr.EstateMS.common.model.BaseModel;
import com.hnwlxy.zr.EstateMS.common.vo.SysRoleVo;

import java.util.Map;

public interface RoleService {
     void selectPageRole(BaseModel baseModel) throws Exception;
     void insertRoleVo(SysRoleVo roleVo, BaseModel baseModel) throws Exception;
     void selectRoleVoByPk(BaseModel baseModel, int pk) throws  Exception;
     void updateRoleVo(SysRoleVo roleVo, BaseModel baseModel) throws Exception;
     void deleteRoleInPk(String roleIds, BaseModel baseModel) throws Exception;
     void selectRoleName(BaseModel baseModel) throws Exception;
     Map<String,String> selectMergeRolePermissionByUserId(int userId) throws Exception;
}

package com.hnwlxy.zr.EstateMS.biz.service;

import com.hnwlxy.zr.EstateMS.common.model.BaseModel;

import java.util.Map;

public interface MenuService {
   void selectAllMenu()throws Exception;

   void refreshSysMenu(BaseModel baseModel) throws  Exception;

   void findMyMenu(Map<String, String> map, BaseModel baseModel) throws Exception;
}

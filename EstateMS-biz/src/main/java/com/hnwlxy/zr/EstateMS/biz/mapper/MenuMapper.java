package com.hnwlxy.zr.EstateMS.biz.mapper;


import com.hnwlxy.zr.EstateMS.common.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<Menu> selectAllMenu();
}

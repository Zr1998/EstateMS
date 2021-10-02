package com.hnwlxy.zr.EstateMS.biz.mapper;


import com.hnwlxy.zr.EstateMS.common.model.QueryParams;
import com.hnwlxy.zr.EstateMS.common.pojo.Developer;
import com.hnwlxy.zr.EstateMS.common.pojo.Estate;
import com.hnwlxy.zr.EstateMS.common.pojo.HouseType;
import com.hnwlxy.zr.EstateMS.common.pojo.ViewType;
import com.hnwlxy.zr.EstateMS.common.vo.EstateVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EstateMapper {
    //分页查询房屋列表
    List<EstateVo> selectEstateVo(QueryParams queryParams) throws Exception;

    EstateVo selectEstateVoByEstateId(@Param("estateId") int estateId) throws Exception;

    Estate selectEstateByEstateId(@Param("estateId") int estateId);

    Estate selectEstateByEstateName(String estate_name);

    List<Developer> selectEstateDeveloper();

    List<ViewType> selectEstateView();

    List<HouseType> selectHouseType();

    int insertEstate(Estate estate);

    int updateEstateVo(Estate estate)throws Exception;

    int deleteEstate(@Param("estateIds") String estateIds);

    int tiggerState(Estate estate);

    int changeStateByCustomerId(@Param("customerIds") String customerIds);

    int tiggerStateByCustomerId(@Param("customerIds") int customerIds);

    List<Estate>  selectEstateName();
}

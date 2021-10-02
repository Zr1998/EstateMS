package com.hnwlxy.zr.EstateMS.biz.mapper;


import com.hnwlxy.zr.EstateMS.common.pojo.FileLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FileLogMapper {
    int insertFileLog(FileLog filelog);

    int removeFileLogByUserIds(@Param("userIds") String userIds);

    int removeFileLogByEstateIds(@Param("estateIds") String estateIds);
}

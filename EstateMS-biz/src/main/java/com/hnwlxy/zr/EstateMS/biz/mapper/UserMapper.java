package com.hnwlxy.zr.EstateMS.biz.mapper;


import com.hnwlxy.zr.EstateMS.common.model.QueryParams;
import com.hnwlxy.zr.EstateMS.common.pojo.User;
import com.hnwlxy.zr.EstateMS.common.pojo.UserRole;
import com.hnwlxy.zr.EstateMS.common.vo.SysUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    SysUserVo selectUserByAccount(@Param("user_account") String user_account);

    int updateBySessionId(User user);

    SysUserVo selectUserVoByUserId(@Param("user_id") int user_id);

    int insertUser(User user);

    int insertUserRole(UserRole userRole);

    int insertUserRoleId(UserRole userRole);

    //分页查询用户列表
    List<SysUserVo> selectUserVo(QueryParams queryParams);

    int updateUserVo(User user);

    int removeUserInPk(@Param("userIds") String userIds);

    int deleteRoleNames(@Param("fkUserIds") String fkUserIds);

    int updatePassword(User user);

    int resetPassword(User user) throws Exception;
}

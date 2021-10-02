package com.hnwlxy.zr.EstateMS.biz.mapper;


import com.hnwlxy.zr.EstateMS.common.model.QueryParams;
import com.hnwlxy.zr.EstateMS.common.pojo.Role;
import com.hnwlxy.zr.EstateMS.common.pojo.RolePermission;
import com.hnwlxy.zr.EstateMS.common.vo.SysRoleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface RoleMapper {
    //查询所有角色
    List<Role> selectRoleName();
    List<Role> selectRole(QueryParams queryParams) throws SQLException;

    int insertRole(Role role);
    int insertRolePermission(RolePermission rolepermission);
    int countRoleByName(@Param("name") String name);
    Role selectRoleByName(@Param("name") String name);
    SysRoleVo selectRoleVoByPk(@Param("pk") int pk);
    int updateRoleVo(Role role);

    int deleteUserRoleInRoleIds(@Param("roleIds") String roleIds);
    int deleteRolePermission(@Param("fkRoleIds") String fkRoleIds);
    int removeRoleInPk(@Param("roleIds") String roleIds);

    List<RolePermission> selectRolePermissionByUserId(@Param("userId") int userId);
}

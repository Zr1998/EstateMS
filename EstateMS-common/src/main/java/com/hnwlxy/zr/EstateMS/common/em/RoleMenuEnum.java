package com.hnwlxy.zr.EstateMS.common.em;

/*
 * @title:<h3> 查询权限菜单枚举类 <h3>
 * @author: Zr
 * @date: 2021/1/14  11:02
 * @params
 * @return
 **/
public enum RoleMenuEnum {
    NO_2(2,"Swagger","查询"),
    NO_201(201,"角色管理","查询,新增,修改,删除"),
    NO_202(202,"用户管理","查询,新增,修改,删除,导入,导出"),
    NO_101(101,"操作日志","查询"),
    No_301(301,"房源管理","查询,新增,修改,删除"),
    No_302(302,"客户管理","查询,新增,修改,删除"),
    No_303(303,"销售管理","查询,新增"),
    ;



    int code;
    String name;
    String value;

    RoleMenuEnum(int code, String name, String value){
        this.code=code;
        this.name=name;
        this.value=value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}

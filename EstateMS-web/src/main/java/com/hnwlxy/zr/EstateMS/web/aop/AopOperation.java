package com.hnwlxy.zr.EstateMS.web.aop;

import com.hnwlxy.zr.EstateMS.common.em.RoleMenuEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//标注注解用于方法
@Retention(RetentionPolicy.RUNTIME)//可以反射获得注解参数
public @interface AopOperation {
    /*
     * @title:<h3> 描述信息 <h3>
     * @author: Zr
     * @date: 2021/4/20  10:49
     * @params []
     * @return java.lang.String
     **/
    String desc() default "";

    /*
     * @title:<h3> 操作类型 <h3>
     * @author: Zr
     * @date: 2021/4/20  10:49
     * @params []
     * @return java.lang.String
     **/
    String type();

    RoleMenuEnum menu();

/*
 * @title:<h3> 是否记录操作日志 <h3>
 * @author: Zr
 * @date: 2021/4/20  10:49
 * @params []
 * @return boolean
 **/
    boolean saveLog() default true;
}

package com.hnwlxy.zr.EstateMS.web.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//标注注解用于方法
@Retention(RetentionPolicy.RUNTIME)//可以反射获得注解参数
public @interface AopSubmit {
}

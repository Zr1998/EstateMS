package com.hnwlxy.zr.EstateMS.web.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AopAspect {
    Logger log=Logger.getLogger(AopAspect.class);

    @Pointcut("execution(* com.hnwlxy.zr.EstateMS.web.controller..*.*(..))")
    public void pointCut(){

    }

    @Before("pointCut()")
    public void before(JoinPoint point)throws Exception{
        log.error("【前置通知，方法执行前完成】"+point.getTarget().getClass().getName()+"."+point.getSignature().getName());
    }

    @AfterReturning(pointcut = "pointCut()")
    public void afterReturn(JoinPoint point) {
        log.error("【后置通知，方法执行完成，返回时触发】"+point.getTarget().getClass().getName()+"."+point.getSignature().getName());
    }

    @After("pointCut()")
    public void after(JoinPoint point){
        log.error("【最终通知】"+point.getTarget().getClass().getName()+"."+point.getSignature().getName());
    }

    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint point) throws Exception{
        Object[] arrs=point.getArgs();
        log.error("【环绕通知】"+point.getTarget().getClass().getName()+"."+point.getSignature().getName());
        Object result=null;
        try{
            log.error("前置通知");
            result=point.proceed();//controller的方法
            log.error("后置通知");
        }catch(Throwable e){
            log.error("异常通知");
        }finally {
            log.error("最终通知");
        }
        return result;
    }

    @AfterThrowing(pointcut = "pointCut()",throwing = "error")
    public void afterThrowing(JoinPoint point, Throwable error){
        log.error("【异常通知，方法执行出错触发】"+point.getTarget().getClass().getName()+"."+point.getSignature().getName()+"\n"+error);
    }
}

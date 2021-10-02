package com.hnwlxy.zr.EstateMS.web.aop;


import com.hnwlxy.zr.EstateMS.biz.mapper.OperationLogMapper;
import com.hnwlxy.zr.EstateMS.common.contants.BaseContants;
import com.hnwlxy.zr.EstateMS.common.contants.GlobalVar;
import com.hnwlxy.zr.EstateMS.common.em.ResultMesgEnum;
import com.hnwlxy.zr.EstateMS.common.exception.BusinessException;

import com.hnwlxy.zr.EstateMS.common.model.BaseModel;
import com.hnwlxy.zr.EstateMS.common.pojo.OperationLog;
import com.hnwlxy.zr.EstateMS.common.util.QueryUtil;
import com.hnwlxy.zr.EstateMS.common.vo.SysUserVo;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Aspect
@Component
public class OperationAspect {
    Logger log=Logger.getLogger(OperationAspect.class);
    protected HttpServletRequest request;
    protected HttpSession session;
    protected BaseModel baseModel=new BaseModel();

    @Autowired
    private OperationLogMapper operationLogMapper;

    @Pointcut("execution(* com.hnwlxy.zr.EstateMS.web.controller..*.*(..))")
    public void pointCut(){

    }

    /*
     * @title:<h3> 前置通知，用于获得请求参数， req,session <h3>
     * @author: Zr
     * @date: 2020/12/20 20:58
     * @params [joinPoint, operation]
     * @return void
     **/
    @Before("pointCut()&&@annotation(operation)")
    public void doBefore(JoinPoint joinPoint, AopOperation operation) throws Exception {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        session = request.getSession();
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            if (args[i].getClass() == BaseModel.class) {
                baseModel = (BaseModel) args[i];
            }
            //  log.error("参数["+(i+1)+"]:"+JSON.toJSONString(args[i]));
        }
        //判断是否有权限
        checkOperationPermission(operation);
        //转化查询参数
        QueryUtil.convertAdvancedQuery(baseModel);
    }

//        //判断是否有权限
//        String code=operation.menu().getCode()+"";//获得权限编号
//        //判断当前操作类型在权限值中的序列
//        int num = -1;//存储权限值序列
//        String typeNames[]=operation.menu().getValue().split(",");
//        for(int i=0;i<typeNames.length;i++){
//            if (operation.type().equals(typeNames[i])){
//                num=i;
//                break;
//            }
//        }
//        if(num<0){ //操作类型不存在
//            throw new BusinessException(ErrorMesgEnum.NO_PERMISSIONS.getMesg()
//                    .replace("{module}",operation.menu().getName())
//                    .replace("{type}",operation.type()));
//
//        }
//        //从session中获的当前登录用户的权限值
//        Map<String,String> mapPermission=(Map<String,String>)session.getAttribute(BaseContants.LOGIN_PERMISSION);
//        if(mapPermission==null){//未登录
//            throw  new BusinessException(ErrorMesgEnum.NO_LOGIN);
//        }
//        String permissionValue=mapPermission.get(code);//获得当前操作模块的权限值
//        if(permissionValue==null){ //没有该模块的权限
//            throw new BusinessException(ErrorMesgEnum.NO_PERMISSIONS.getMesg()
//                    .replace("{module}",operation.menu().getName())
//                    .replace("{type}",operation.type()));
//        }else if(permissionValue.length()<num+1){ //找不到操作的权限序列
//            throw new BusinessException(ErrorMesgEnum.NO_PERMISSIONS.getMesg()
//                    .replace("{module}",operation.menu().getName())
//                    .replace("{type}",operation.type()));
//        }else {
//            //找到权限序列后，判断是否有该权限值
//            if(permissionValue.charAt(num)!='1'){
//                //说明没该权限
//                throw new BusinessException(ErrorMesgEnum.NO_PERMISSIONS.getMesg()
//                        .replace("{module}",operation.menu().getName())
//                        .replace("{type}",operation.type()));
//            }
//        }
//        //返回无权限按钮的dom选择
//
//        //1111101;查询，修改，删除
//        //.permission.search,.permission.add,.permission_delete,.permission_update
//        //获得全部由权限控制的dom选择器
//        String ids=GlobalVar.mapCodeMenu.get(code).getIds();
//        StringBuilder notPermissionBtn=new StringBuilder("");
//        if(ids!=null){
//            String arrId[]=ids.split(";");
//            for(int i=0;i<arrId.length&&i<permissionValue.length();i++){
//                if(permissionValue.charAt(i)!='1'){
//                    //说明没权限
//                    notPermissionBtn.append(",");
//                    notPermissionBtn.append(arrId[i]);//,.permission_delete,.permission_update,.permission.search"
//                }
//            }
//        }
//        if(notPermissionBtn.length()>0){//去掉第一个逗号
//            notPermissionBtn.replace(0,1,"");
//        }
//        baseModel.setPermission_btns(notPermissionBtn.toString());


    /**
     * @title:<h3> 判断是否有权限 <h3>
     * @author: Zr
     * @date: 2020-12-20 14:23
     * @params [operation]
     * @return void
     **/
    private void checkOperationPermission(AopOperation operation)throws Exception{
        String code = operation.menu().getCode() + "";//获得权限编号
        //判断当前操作类型在权限值中的序列
        int num = -1;//存储权限值序列
        String[] typeNames = operation.menu().getValue().split(",");
        for (int i = 0; i < typeNames.length; i++) {
            if (operation.type().equals(typeNames[i])) {
                num = i;
                break;
            }
        }
        if (num < 0) {//操作类型不存在
            throw new BusinessException(ResultMesgEnum.NO_PERMISSIONS.getMesg()
                    .replace("{module}", operation.menu().getName())
                    .replace("{type}", operation.type()));

        }
        //从session获得当前登录用户的权限值
        Map<String, String> mapPermission = (Map<String, String>) session.getAttribute(BaseContants.LOGIN_PERMISSION);
        if (mapPermission == null) {//未登录
            throw new BusinessException(ResultMesgEnum.NO_LOGIN);
        }
        String permissionValue=mapPermission.get(code);//获得当前操作模块的权限值
        if(permissionValue==null){//没有该模块的权限
            throw new BusinessException(ResultMesgEnum.NO_PERMISSIONS.getMesg()
                    .replace("{module}", operation.menu().getName())
                    .replace("{type}", operation.type()));
        }else if(permissionValue.length()<num+1){//找不到操作的权限序列
            throw new BusinessException(ResultMesgEnum.NO_PERMISSIONS.getMesg()
                    .replace("{module}", operation.menu().getName())
                    .replace("{type}", operation.type()));
        }else{
            //找到权限序列后，判断是否有该权限值
            if(permissionValue.charAt(num)!='1'){//说明没权限
                throw new BusinessException(ResultMesgEnum.NO_PERMISSIONS.getMesg()
                        .replace("{module}", operation.menu().getName())
                        .replace("{type}", operation.type()));
            }
        }
        //返回无权限按钮的dom选择器
        //1111101；查询，修改，删除
        //.permission_search;.permission_add;.permission_update;.permission_delete;
        //获取全部由权限控制的dom选择器
        String ids=GlobalVar.mapCodeMenu.get(code).getIds();
        StringBuilder permissionBtn=new StringBuilder("");
        if(ids!=null){
            String[] arrId=ids.split(";");
            for(int i=0;i<arrId.length&&i<permissionValue.length();i++){
                if(permissionValue.charAt(i)=='1'){//说明有权限
                    permissionBtn.append(",");
                    permissionBtn.append(arrId[i]);
                    //,.permission_delete,.permission_update,.permission_search
                }
            }
        }
        if(permissionBtn.length()>0){//去掉第一个逗号
            permissionBtn.replace(0,1,"");
        }
        baseModel.setPermission_btns(permissionBtn.toString());
    }


    @After("pointCut()&&@annotation(operation)")
    public void doAfter(JoinPoint joinPoint, AopOperation operation){
        //log.error("查看切面数据："+ JSON.toJSONString(operation));
        OperationLog operationlog=new OperationLog();
        operationlog.setRequest_ip(request.getRemoteAddr());
        SysUserVo userVo=(SysUserVo) session.getAttribute(BaseContants.LOGIN_USER);
        if (userVo.getUser()==null){
            throw new BusinessException(ResultMesgEnum.NO_LOGIN);
        }
        operationlog.setUser_account(userVo.getUser().getUser_account());
        operationlog.setUser_id(userVo.getUser().getUser_id());
        operationlog.setUser_name(userVo.getUser().getUser_name());
        operationlog.setModule(operation.menu().getName());
        operationlog.setType(operation.type());
        operationlog.setContent(operation.desc()+"|"+baseModel.getAop_mesg());
        operationlog.setRequest_method(joinPoint.getTarget().getClass().getName()+"."+joinPoint.getSignature());
        if(operation.saveLog()){
            int count=operationLogMapper.insert(operationlog);
            if (count==0){
                throw new BusinessException("新增操作日志失败");
            }
        }

    }
}

package com.hnwlxy.zr.EstateMS.web.baseController;

import com.hnwlxy.zr.EstateMS.common.contants.BaseContants;
import com.hnwlxy.zr.EstateMS.common.em.ResultMesgEnum;
import com.hnwlxy.zr.EstateMS.common.exception.BusinessException;
import com.hnwlxy.zr.EstateMS.common.vo.SysUserVo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

/*
 * @title:<h3>  <h3>
 * @author: Zr
 * @date: 2018/11/12  15:47
 * @params
 * @return
 **/
@Controller
@ResponseBody
public class BaseController {
    private static Logger log= org.apache.log4j.Logger.getLogger(BaseController.class);
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    @InitBinder()
    public void initHttpParams(HttpServletRequest req, HttpServletResponse res){
        request=req;
        response=res;
        session=req.getSession();
    }

    /*
     * @title:<h3> baseModel 参数前缀<h3>
     * @author: Zr
     * @date: 2018/11/13 13:35
     * @params [binder]
     * @return void
     **/
    @InitBinder("baseModel")
    public void initBinder1(WebDataBinder binder){

        binder.setFieldDefaultPrefix("baseModel.");
    }

    /*
     * @title:<h3> 统一处理业务异常 <h3>
     * @author: Zr
     * @date: 2018/11/13  14:50
     * @params [ex]
     * @return com.hnkjzyxy.base.exception.BusinessException
     **/
    @ExceptionHandler(BusinessException.class)
    public BusinessException businessException(BusinessException ex){
        if(ex.getCode()==0){
            ex.setCode(1000);//业务异常默认错误状态码
        }
        log.error(ex.getMessage(),ex);
        response.setStatus(ex.getCode());
        return ex;
    }

    /*
     * @title:<h3> 统一处理SQL异常 <h3>
     * @author: Zr
     * @date: 2018/11/13  14:51
     * @params [ex]
     * @return com.hnkjzyxy.base.exception.BusinessException
     **/
    @ExceptionHandler(SQLException.class)
    public BusinessException SQLException(SQLException ex){
        log.error(ex.getMessage(),ex);
        BusinessException bex=new BusinessException(ResultMesgEnum.SQL_ERROR);
        bex.setStackTrace(ex.getStackTrace());
        return  businessException(bex);
    }

/*
 * @title:<h3> 统一处理系统异常 <h3>
 * @author: Zr
 * @date: 2018/11/13  14:51
 * @params [ex]
 * @return com.hnkjzyxy.base.exception.BusinessException
 **/
    @ExceptionHandler(Exception.class)
    public BusinessException exception(Exception ex){
       log.error(ex.getMessage(),ex);
        BusinessException bex=new BusinessException();
        bex.setStackTrace(ex.getStackTrace());
        if ("org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator".equals(ex.getStackTrace()[0].getClassName())){
           bex.setMessage(ResultMesgEnum.SQL_ERROR);
        }else {
            bex.setMessage(ResultMesgEnum.SYS_ERROR);
        }
        return  businessException(bex);
    }

    /*
     * @title:<h3> 获取sesson用户信息 <h3>
     * @author: Zr
     * @date: 2018/11/14 15:59
     * @params []
     * @return com.hnkjzyxy.pojo.User
     **/
    protected SysUserVo getSessionUser()throws Exception{
        SysUserVo user = (SysUserVo) session.getAttribute(BaseContants.LOGIN_USER);
        if (user==null){
            throw  new BusinessException(ResultMesgEnum.NO_LOGIN);
        }
        return user;
    }


}

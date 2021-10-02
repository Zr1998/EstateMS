package com.hnwlxy.zr.EstateMS.common.exception;


import com.hnwlxy.zr.EstateMS.common.em.ResultMesgEnum;
import io.swagger.annotations.ApiModelProperty;

/*
 * @title:<h3> 业务异常类 <h3>
 * @author: Zr
 * @date: 2020/11/13  13:46
 * @params
 * @return
 **/
public class BusinessException extends RuntimeException{
    @ApiModelProperty("异常状态码")
    int code=0;
    @ApiModelProperty("异常消息")
    String message;

    public BusinessException(int code,String message){
        this.code=code;
        this.message=message;
    }

    public BusinessException(){}

    public BusinessException(String message){
        this.message=message;
    }
    public BusinessException(ResultMesgEnum em){
        this.code=em.getCode();
        this.message=em.getMesg();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message){
        this.message=message;
    }
    public void setMessage(ResultMesgEnum em) {
        this.code=em.getCode();
        this.message = em.getMesg();
    }
}

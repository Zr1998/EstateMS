package com.hnwlxy.zr.EstateMS.common.util;


import com.hnwlxy.zr.EstateMS.common.em.ResultMesgEnum;
import com.hnwlxy.zr.EstateMS.common.model.BaseModel;


public class ResultUtil {
    public static BaseModel success(Object object) {
        BaseModel result = new BaseModel();
        result.setMessage(ResultMesgEnum.SUCCESS.getMesg());
        result.setMsgCode(ResultMesgEnum.SUCCESS.getMsgCode());
        result.setResultCode(ResultMesgEnum.SUCCESS.getCode());
        result.setData( object);
        return result;
    }

    public static BaseModel success() {
        return success(null);
    }


    public static BaseModel error(Integer code,String codeMsg,String msg) {
        BaseModel result = new BaseModel();
        result.setResultCode(code);
        result.setMsgCode(codeMsg);
        result.setMessage(msg);
        return result;
    }

    public static BaseModel error(String msg) {
        BaseModel result = new BaseModel();
        result.setResultCode(ResultMesgEnum.ERROR.getCode());
        result.setMsgCode(ResultMesgEnum.ERROR.getMsgCode());
        result.setMessage(msg);
        return result;
    }
}

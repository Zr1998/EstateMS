package com.hnwlxy.zr.EstateMS.common.util;

import java.util.regex.Pattern;

public class RegularExpressionUtil {
    //验证特殊字符
    public  static String SPECIAL_CODE="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

    /*
     * @title:<h3> 正则表达式验证 <h3>
     * @author: Zr
     * @date: 2020/11/22  13:49
     * @params [regular, str]
     * @return java.lang.Boolean
     **/
    public  static Boolean check(String regular,String str) throws Exception{
        return Pattern.matches(regular,str);
    }
}

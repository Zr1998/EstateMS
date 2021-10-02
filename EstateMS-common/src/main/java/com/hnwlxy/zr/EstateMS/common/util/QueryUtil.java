package com.hnwlxy.zr.EstateMS.common.util;



import com.hnwlxy.zr.EstateMS.common.contants.BaseContants;
import com.hnwlxy.zr.EstateMS.common.em.EnumQueryType;
import com.hnwlxy.zr.EstateMS.common.exception.BusinessException;
import com.hnwlxy.zr.EstateMS.common.model.AdvancedQuery;
import com.hnwlxy.zr.EstateMS.common.model.BaseModel;
import com.hnwlxy.zr.EstateMS.common.model.QueryParams;

import java.util.List;

/*
 * @title:<h3>查询工具类  <h3>
 * @author: Zr
 * @date: 2020/11/22  11:07
 * @params
 * @return
 **/
public class QueryUtil {
    /*
     * @title:<h3> 转化高级查询条件 <h3>
     * @author: Zr
     * @date: 2020/11/22  14:38
     * @params [baseModel]
     **/
    public static void convertAdvancedQuery(BaseModel baseModel) throws Exception{
        QueryParams queryParams=convertAdvancedQuery(baseModel.getListAdvancedQuery());
        queryParams.setCurr_page(baseModel.getQueryParams().getCurr_page());
        queryParams.setPage_size(baseModel.getQueryParams().getPage_size());
        baseModel.setQueryParams(queryParams);
    }

    /*
     * @title:<h3> 转化高级查询条件 <h3>
     * @author: Zr
     * @date: 2020/11/22  11:10
     * @params [listAdvancedQuery]
     **/
    public static QueryParams convertAdvancedQuery(List<AdvancedQuery> listAdvancedQuery) throws Exception{
        QueryParams queryParams=new QueryParams();
        StringBuilder where=new StringBuilder("");
        if(listAdvancedQuery!=null&&listAdvancedQuery.size()>0){
            for(int i=0;i<listAdvancedQuery.size();i++){ //解析高级查询条件
                where.append(" ");
                where.append(convertAdvancedQueryItem(listAdvancedQuery.get(i))); //and user_name='zhangsan'
            }
        }
        //去掉查询条件的第一个and或者or
        if(where.toString().trim().indexOf("AND")==0){
            queryParams.setWhere("("+where.toString().trim().substring(3)+")");
        }else if(where.toString().trim().indexOf("OR")==0){
            queryParams.setWhere("("+where.toString().trim().substring(3)+")");
        }else {
            queryParams.setWhere(where.toString().trim());
        }
        return  queryParams;
    }

    /*
     * @title:<h3> 转化sql语句返回 <h3>
     * @author: Zr
     * @date: 2020/11/22  11:18
     * @params [advancedQuery]
     * @return java.lang.String
     **/
    private  static String convertAdvancedQueryItem(AdvancedQuery advancedQuery) throws Exception{
        StringBuilder where=new StringBuilder("");
        //如果没有查询的字段或字段的值，则不需要组装查询条件
        if(advancedQuery.getFieldName()==null||"".equals(advancedQuery.getFieldName())
                || advancedQuery.getFieldValue()==null ||"".equals(advancedQuery.getFieldValue())){
            return "";
        }
        //验证字段类型，设置默认的关系运算符
        if(advancedQuery.getFieldType()==null||"".equals(advancedQuery.getFieldType())){
            //设置默认字段类型为varchar
            advancedQuery.setFieldType(BaseContants.FIELD_TYPE.VARCHAR);
        }
        //验证逻辑运算符合和关系运算符是否符合配置的参数
        if(advancedQuery.getLogicalOperator()!=null &&EnumQueryType.getLogicalByTag(advancedQuery.getLogicalOperator())==null){
            throw new BusinessException("高级查询条件异常：逻辑运算符异常");
        }else {
            where.append(EnumQueryType.getLogicalByTag(advancedQuery.getLogicalOperator()));//逻辑运算符（and/or)
        }
        where.append(" ");
        //验证字段
        if(RegularExpressionUtil.check(RegularExpressionUtil.SPECIAL_CODE,advancedQuery.getFieldName())){
            throw new BusinessException("高级查询字段不能含有特殊字符");
        }
        where.append(advancedQuery.getFieldName());//字段
        where.append(" ");

        //设置默认关系运算符为等于
        if(advancedQuery.getTempOperator()==null||"".equals(advancedQuery.getTempOperator())){
            if (BaseContants.FIELD_TYPE.VARCHAR.equals(advancedQuery.getFieldType())) {//varchar的默认关系运算符为like
                advancedQuery.setTempOperator(EnumQueryType.RELATION_LIKE.getTag());
            } else {
                advancedQuery.setTempOperator(EnumQueryType.RELATION_QUERY.getTag());
            }
        }else if(advancedQuery.getTempOperator()!=null&&EnumQueryType.getLogicalByTag(advancedQuery.getTempOperator())==null){
            throw new BusinessException("高级查询条件异常：关系运算符异常");
        } //将临时关系运算符转化为实际关系运算符
            advancedQuery.setRelationOperator(EnumQueryType.getLogicalByTag(advancedQuery.getTempOperator()));
        //组装条件
        if (EnumQueryType.RELATION_QUERY.getTag().equals(advancedQuery.getTempOperator())){ //如果是等于
            //如果是时间类型，则进行时间段查询
            //2018-01-01(2018-01-01 00;00:00-2018-01-01 23:59:59)
            if(BaseContants.FIELD_TYPE.DATE.equals(advancedQuery.getFieldType())){ //date
                where.append(">=");//关系运算符（大于、等于..）
                where.append("'");
                where.append(advancedQuery.getFieldValue()+" 00:00:00");//字段值
                where.append("'");
                where.append(" and ");
                where.append("<=");
                where.append("'");
                where.append(advancedQuery.getFieldValue()+" 23:59:59");//字段值
                where.append("'");
            }else {
                where.append(advancedQuery.getRelationOperator());//关系运算符（大于、等于）
                where.append("'");
                where.append(advancedQuery.getFieldValue());//字段值
                where.append("'");
            }
        }else if(EnumQueryType.RELATION_GREATER.getTag().equals(advancedQuery.getTempOperator())
                ||EnumQueryType.RELATION_LESS.getTag().equals(advancedQuery.getTempOperator())
                ||EnumQueryType.RELATION_NOTLESS.getTag().equals(advancedQuery.getTempOperator())
                ||EnumQueryType.RELATION_NOTGREATER.getTag().equals(advancedQuery.getTempOperator())){ //如果是大于、小于、大于等于、小于等于
            where.append(advancedQuery.getRelationOperator());
            where.append("'");
            where.append(advancedQuery.getFieldValue());//字段值
            where.append("'");
        }else if(EnumQueryType.RELATION_LIKE.getTag().equals(advancedQuery.getTempOperator())){//如果是包含
            where.append(advancedQuery.getRelationOperator());
            where.append("'%");
            where.append(advancedQuery.getFieldValue());//字段值
            where.append("%'");
        }else if(EnumQueryType.RELATION_LIKESTAR.getTag().equals(advancedQuery.getTempOperator())){ //如果是以..开始
            where.append(advancedQuery.getRelationOperator());
            where.append("'");
            where.append(advancedQuery.getFieldValue());//字段值
            where.append("%'");
        }else if(EnumQueryType.RELATION_LIKEEND.getTag().equals(advancedQuery.getTempOperator())){ //如果是以..结尾
            where.append(advancedQuery.getRelationOperator());
            where.append("'%");
            where.append(advancedQuery.getFieldValue());//字段值
            where.append("'");
        }else {
            where.append(advancedQuery.getRelationOperator());//关系运算符（大于、等于）
            where.append("'");
            where.append(advancedQuery.getFieldValue());//字段值
            where.append("'");
        }
        return where.toString();
    }
}

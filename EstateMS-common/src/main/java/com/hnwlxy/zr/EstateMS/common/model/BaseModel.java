package com.hnwlxy.zr.EstateMS.common.model;


import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class BaseModel {
    @ApiModelProperty("定义返回状态码，'200'为程序执行成功")
    private Integer resultCode = 200;//定义返回的状态码
    @ApiModelProperty("定义返回状态码的消息")
    private String MsgCode;//定义返回状态码的消息
    @ApiModelProperty("定义返回的错误信息")
    private String Message;//定义一个消息
    @ApiModelProperty("定义返回的数据集合")
    private Object data;//数据集
    @ApiModelProperty("定义分页参数")
    private QueryParams queryParams = new QueryParams();
    @ApiModelProperty("文件上传对象")
    private MultipartFile[] tempMFile;
    @ApiModelProperty("用于存放上传文件的id")
    private String filesArray;
    @ApiModelProperty("aop保存参数")
    private String aop_mesg;
    @ApiModelProperty("无权限的dom选择器")
    private String permission_btns;
    @ApiModelProperty("高级查询条件")
    private List<AdvancedQuery> listAdvancedQuery;
    @ApiModelProperty("表单防重")
    private String token;

    public String getMsgCode() {
        return MsgCode;
    }

    public void setMsgCode(String msgCode) {
        MsgCode = msgCode;
    }

    public String getAop_mesg() {
        return aop_mesg;
    }

    public void setAop_mesg(String aop_mesg) {
        this.aop_mesg = aop_mesg;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public QueryParams getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(QueryParams queryParams) {
        this.queryParams = queryParams;
    }

    public MultipartFile[] getTempMFile() {
        return tempMFile;
    }

    public void setTempMFile(MultipartFile[] tempMFile) {
        this.tempMFile = tempMFile;
    }

    public String getFilesArray() {
        return filesArray;
    }

    public void setFilesArray(String filesArray) {
        this.filesArray = filesArray;
    }

    public String getPermission_btns() {
        return permission_btns;
    }

    public void setPermission_btns(String permission_btns) {
        this.permission_btns = permission_btns;
    }

    public List<AdvancedQuery> getListAdvancedQuery() {
        return listAdvancedQuery;
    }

    public void setListAdvancedQuery(List<AdvancedQuery> listAdvancedQuery) {
        this.listAdvancedQuery = listAdvancedQuery;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

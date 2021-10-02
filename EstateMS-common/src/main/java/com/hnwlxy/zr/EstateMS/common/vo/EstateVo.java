package com.hnwlxy.zr.EstateMS.common.vo;


import com.hnwlxy.zr.EstateMS.common.pojo.*;
import io.swagger.annotations.ApiModelProperty;

/*
 * @title:<h3> 房屋Vo <h3>
 * @author: Zr
 * @date: 2020/12/7  13:35
 * @params
 * @return
 **/
public class EstateVo {
    @ApiModelProperty(value = "房屋id")
    private int estate_id;
    private String developer_name;
    private String hType_name;
    private String vType_name;
    private Estate estate;
    private Developer developer;
    private ViewType viewType;
    private HouseType houseType;
    @ApiModelProperty("房屋图片文件信息")
    private FileLog fileLog;
    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public ViewType getViewType() {
        return viewType;
    }

    public void setViewType(ViewType viewType) {
        this.viewType = viewType;
    }

    public HouseType getHouseType() {
        return houseType;
    }

    public void setHouseType(HouseType houseType) {
        this.houseType = houseType;
    }

    public int getEstate_id() {
        return estate_id;
    }

    public void setEstate_id(int estate_id) {
        this.estate_id = estate_id;
    }

    public String getDeveloper_name() {
        return developer_name;
    }

    public void setDeveloper_name(String developer_name) {
        this.developer_name = developer_name;
    }

    public String gethType_name() {
        return hType_name;
    }

    public void sethType_name(String hType_name) {
        this.hType_name = hType_name;
    }

    public String getvType_name() {
        return vType_name;
    }

    public void setvType_name(String vType_name) {
        this.vType_name = vType_name;
    }

    public Estate getEstate() {
        return estate;
    }

    public void setEstate(Estate estate) {
        this.estate = estate;
    }

    public FileLog getFileLog() {
        return fileLog;
    }

    public void setFileLog(FileLog fileLog) {
        this.fileLog = fileLog;
    }
}

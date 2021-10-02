package com.hnwlxy.zr.EstateMS.common.pojo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class Estate implements Serializable {
    @ApiModelProperty(value = "房屋编号")
    private int estate_id;

    @ApiModelProperty(value = "小区名称")
    private String estate_name;

    @ApiModelProperty(value = "楼号")
    private String estate_buildingNum;

    @ApiModelProperty(value = "单元号")
    private String estate_unitNum;

    @ApiModelProperty(value = "楼层")
    private String estate_floorNum;

    @ApiModelProperty(value = "房间号")
    private String estate_roomNum;

    @ApiModelProperty(value = "房型编号")
    private int estate_houseType_id;

    @ApiModelProperty(value = "房屋面积")
    private String estate_area;

    @ApiModelProperty(value = "房屋格局")
    private String estate_structure;

    @ApiModelProperty(value = "房产价格")
    private String estate_price;

    @ApiModelProperty(value = "房屋图片")
    private String estate_photo;

    @ApiModelProperty(value = "房屋朝向编号")
    private int estate_viewId;

    @ApiModelProperty(value = "房屋开发商编号")
    private int estate_developer_id;

    @ApiModelProperty(value = "房屋销售状态")
    private int estate_state;

    @ApiModelProperty(value = "是否删除")
    private int is_deleted;

    @ApiModelProperty(value = "创建人id")
    private int create_user_id;

    @ApiModelProperty(value = "创建人名称")
    private String create_user_name;

    @ApiModelProperty(value = "创建时间")
    private Date create_time;

    @ApiModelProperty(value = "更新人用户id")
    private int update_user_id	;

    @ApiModelProperty(value = "更新人用户名")
    private String update_user_name;

    @ApiModelProperty(value = "更新时间")
    private Date update_time;


    public int getEstate_id() {
        return estate_id;
    }

    public void setEstate_id(int estate_id) {
        this.estate_id = estate_id;
    }

    public String getEstate_name() {
        return estate_name;
    }

    public void setEstate_name(String estate_name) {
        this.estate_name = estate_name;
    }

    public String getEstate_buildingNum() {
        return estate_buildingNum;
    }

    public void setEstate_buildingNum(String estate_buildingNum) {
        this.estate_buildingNum = estate_buildingNum;
    }

    public String getEstate_unitNum() {
        return estate_unitNum;
    }

    public void setEstate_unitNum(String estate_unitNum) {
        this.estate_unitNum = estate_unitNum;
    }

    public String getEstate_floorNum() {
        return estate_floorNum;
    }

    public void setEstate_floorNum(String estate_floorNum) {
        this.estate_floorNum = estate_floorNum;
    }

    public String getEstate_roomNum() {
        return estate_roomNum;
    }

    public void setEstate_roomNum(String estate_roomNum) {
        this.estate_roomNum = estate_roomNum;
    }

    public int getEstate_houseType_id() {
        return estate_houseType_id;
    }

    public void setEstate_houseType_id(int estate_houseType_id) {
        this.estate_houseType_id = estate_houseType_id;
    }

    public String getEstate_structure() {
        return estate_structure;
    }

    public void setEstate_structure(String estate_structure) {
        this.estate_structure = estate_structure;
    }

    public String getEstate_photo() {
        return estate_photo;
    }

    public void setEstate_photo(String estate_photo) {
        this.estate_photo = estate_photo;
    }

    public int getEstate_viewId() {
        return estate_viewId;
    }

    public void setEstate_viewId(int estate_viewId) {
        this.estate_viewId = estate_viewId;
    }

    public int getEstate_developer_id() {
        return estate_developer_id;
    }

    public void setEstate_developer_id(int estate_developer_id) {
        this.estate_developer_id = estate_developer_id;
    }

    public String getEstate_area() {
        return estate_area;
    }

    public void setEstate_area(String estate_area) {
        this.estate_area = estate_area;
    }

    public String getEstate_price() {
        return estate_price;
    }

    public void setEstate_price(String estate_price) {
        this.estate_price = estate_price;
    }

    public int getEstate_state() {
        return estate_state;
    }

    public void setEstate_state(int estate_state) {
        this.estate_state = estate_state;
    }

    public int getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
    }

    public int getCreate_user_id() {
        return create_user_id;
    }

    public void setCreate_user_id(int create_user_id) {
        this.create_user_id = create_user_id;
    }

    public String getCreate_user_name() {
        return create_user_name;
    }

    public void setCreate_user_name(String create_user_name) {
        this.create_user_name = create_user_name;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public int getUpdate_user_id() {
        return update_user_id;
    }

    public void setUpdate_user_id(int update_user_id) {
        this.update_user_id = update_user_id;
    }

    public String getUpdate_user_name() {
        return update_user_name;
    }

    public void setUpdate_user_name(String update_user_name) {
        this.update_user_name = update_user_name;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}

package com.hnwlxy.zr.EstateMS.common.pojo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class FileLog {
    @ApiModelProperty(value = "主键id")
    private String file_log_id;

    @ApiModelProperty(value = "文件名称")
    private String file_name;

    @ApiModelProperty(value = "文件保存路径")
    private String save_path;//文件保存地址

    @ApiModelProperty(value = "重命名文件")
    private String file_rename;

    @ApiModelProperty(value = "文件大小")
    private long file_Size;

    @ApiModelProperty(value = "文件类型")
    private String file_type;

    @ApiModelProperty(value = "文件长度")
    private String file_Length;

    @ApiModelProperty(value = "上传用户")
    private String uploader;

    @ApiModelProperty(value = "上传时间")
    private Date upload_time;

    @ApiModelProperty(value = "是否删除")
    private Integer isdeleted;

    @ApiModelProperty(value = "异常信息")
    private String message;

    @ApiModelProperty(value = "文件转化状态")
    private Integer state;

    public String getFile_log_id() {
        return file_log_id;
    }

    public void setFile_log_id(String file_log_id) {
        this.file_log_id = file_log_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getSave_path() {
        return save_path;
    }

    public void setSave_path(String save_path) {
        this.save_path = save_path;
    }

    public String getFile_rename() {
        return file_rename;
    }

    public void setFile_rename(String file_rename) {
        this.file_rename = file_rename;
    }

    public long getFile_Size() {
        return file_Size;
    }

    public void setFile_Size(long file_Size) {
        this.file_Size = file_Size;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public String getFile_Length() {
        return file_Length;
    }

    public void setFile_Length(String file_Length) {
        this.file_Length = file_Length;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public Date getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(Date upload_time) {
        this.upload_time = upload_time;
    }

    public Integer getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Integer isdeleted) {
        this.isdeleted = isdeleted;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}

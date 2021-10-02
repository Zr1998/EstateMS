package com.hnwlxy.zr.EstateMS.biz.service;

import com.hnwlxy.zr.EstateMS.common.model.BaseModel;


public interface FileLogService {
    void uploadFiles(BaseModel baseModel, String uploader) throws Exception;
}

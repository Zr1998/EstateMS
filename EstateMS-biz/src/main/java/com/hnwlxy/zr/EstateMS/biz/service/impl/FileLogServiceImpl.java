package com.hnwlxy.zr.EstateMS.biz.service.impl;


import com.hnwlxy.zr.EstateMS.common.contants.BaseContants;
import com.hnwlxy.zr.EstateMS.common.model.BaseModel;
import com.hnwlxy.zr.EstateMS.common.pojo.FileLog;
import lombok.extern.slf4j.Slf4j;
import com.hnwlxy.zr.EstateMS.biz.mapper.FileLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import com.hnwlxy.zr.EstateMS.biz.service.FileLogService;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.UUID;

@Service
@Slf4j
public class FileLogServiceImpl implements FileLogService {
    @Autowired
    private FileLogMapper fileLogMapper;
    /*
     * @title:<h3> 文件上传 <h3>
     * @author: Zr
     * @date:  2021/2/15  15:36
     * @params [baseModel, uploader]
     * @return void
     **/
    public void uploadFiles(BaseModel baseModel, String uploader) throws Exception {
        //1.判断文件是否为空
        if(baseModel.getTempMFile()==null || baseModel.getTempMFile().length==0){
            return;
        }
        StringBuilder fileIds=new StringBuilder("");
        //文件不为空
        for(MultipartFile file:baseModel.getTempMFile()){
            if(file.isEmpty() || file.getSize()==0 || file.getName()==null){ //判断文件是否有效
                return;
            }
            //2.获得原始文件名
            FileLog fileLog=new FileLog();
            fileLog.setFile_name(file.getOriginalFilename());
            //3.获得文件类型
            fileLog.setFile_type(file.getContentType());
            //4.获得文件长度
            fileLog.setFile_Size(file.getSize());
            //5.重命名文件
            fileLog.setFile_rename(UUID.randomUUID().toString()+fileLog.getFile_name().substring(fileLog.getFile_name().lastIndexOf(".")));
            //6.保存文件，获得保存文件的相对地址
            Calendar c=Calendar.getInstance();//可以对每个时间域单独修改
            int year=c.get(Calendar.YEAR);
            int month=c.get(Calendar.MONTH)+1;
            int date=c.get(Calendar.DATE);
            fileLog.setSave_path(year+File.separator+year+month+File.separator+year+month+date+File.separator+fileLog.getFile_rename());
            //上传者
            fileLog.setUploader(uploader);
            //进行文件上传
                //文件上传物理路径
            String path=System.getProperty(BaseContants.WEBAPP_ROOT)+File.separator+"EstateMS-web"+File.separator+"src"
                    +File.separator+"main"+File.separator+"resources"+File.separator+"static"+File.separator+"files"+File.separator+"upload"+File.separator+fileLog.getSave_path();
            uploaderFile(file,path);
            //7.执行文件新增的sql
            fileLogMapper.insertFileLog(fileLog);
            fileIds.append(",");
            fileIds.append(fileLog.getFile_log_id());
        }
        //8.返回文件上传id
        if(fileIds.length()>1){
            baseModel.setFilesArray(fileIds.toString().substring(1));
        }
    }

    /*
     * @title:<h3> 上传文件 <h3>
     * @author: Zr
     * @date:  2021/2/15  15:36
     * @params [file, desPath]
     * @return void
     **/
    private void uploaderFile(MultipartFile file, String desPath) throws  Exception{
        //判断文件上传的目录是否存在，若不存在则创建文件夹
        File desFile=new File(desPath);//设置目标文件
        if(!desFile.getParentFile().exists()){ //目标文件的文件夹是否存在，若不存在则创建文件夹
            desFile.getParentFile().mkdirs();
        }
        FileOutputStream outputStream = new FileOutputStream(desFile);
        outputStream.write(file.getBytes());
        if(outputStream!=null){
            outputStream.flush();
            outputStream.close();
        }
    }
}

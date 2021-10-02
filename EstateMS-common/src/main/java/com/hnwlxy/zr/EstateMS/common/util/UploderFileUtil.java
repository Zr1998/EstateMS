package com.hnwlxy.zr.EstateMS.common.util;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class UploderFileUtil {
    /*
     * @title:<h3> 文件下载 <h3>
     * @author: Zr
     * @date: 2020/11/21  9:42
     * @params [response, inputStream, fileName]
     * @return void
     **/
    public static void downFile(HttpServletResponse response, InputStream inputStream, String fileName)throws Exception{
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename="+new String(fileName.getBytes(),"iso-8859-1"));
        ServletOutputStream out=response.getOutputStream();
        BufferedInputStream bis=null;
        BufferedOutputStream bos=null;
        try{
            bis=new BufferedInputStream(inputStream);
            bos=new BufferedOutputStream(out);
            byte[] buff=new byte[2048];
            int size=0;
            while ((size=bis.read(buff))>-1){ //循环读取
                bos.write(buff,0,size);
            }
        }catch (final Exception e){
            throw e;
        }finally {
            if (bis!=null){
                bis.close();
            }
            if (bos!=null){
                bos.close();
            }
            if (inputStream!=null){
                inputStream.close();
            }
        }
    }


    public static void download(HttpServletResponse response, File file, String fileName) throws Exception{
        InputStream in=new FileInputStream(file);
        downFile(response,in,fileName);
    }

    /*
     * @title:<h3> 根据绝对路径下载文件 <h3>
     * @author: Zr
     * @date: 2018/11/21  9:53
     * @params [response, filePath, fileName]
     * @return void
     **/
    public static void downFile(HttpServletResponse response, String filePath, String fileName) throws Exception{
        InputStream in=new FileInputStream(filePath);
        downFile(response,in,fileName);
    }
}

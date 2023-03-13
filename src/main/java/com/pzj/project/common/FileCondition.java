package com.pzj.project.common;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileCondition {
    /**
     * 获取文件行数
     * @param multipartFile
     * @return
     */
    public static int getFileLineNum(MultipartFile multipartFile){
        try {
            File file = MultipartFileToFile(multipartFile);
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            int lines = 0;
            while(raf.readLine() != null) lines++;
            raf.close();
            return lines;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 获取文件大小KB
     * @param multipartFile
     * @return
     */
    public static double getFileSize (MultipartFile multipartFile){
        File file = MultipartFileToFile(multipartFile);
        FileChannel fileChannel = null;
        try {
            if(file.exists() && file.isFile()){
                FileInputStream fileInputStream = new FileInputStream(file);
                fileChannel = fileInputStream.getChannel();
                return fileChannel.size()/1024.0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (fileChannel!=null){
                try {
                    fileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }

    /**
     * 将MultipartFile文件转为File
     * @param multipartFile
     * @return
     */
    public static File MultipartFileToFile (MultipartFile multipartFile){
        //获取文件名
        String fileName = multipartFile.getOriginalFilename();
        //获取文件前缀名
        String prefix = fileName.substring(0,fileName.lastIndexOf("."));
        //获取文件后缀名
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        try {
            File file = File.createTempFile(prefix,suffix);
//            multipartFile.transferTo(file);
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.pzj.project.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtils {

    private static final Logger log = LoggerFactory.getLogger(FileUtils.class);


    private static boolean isJsonObj(String linContent) {

        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.parseObject(linContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject ==null?false:true;
    }

    /**
     * 给定根目录，返回一个相对路径所对应的实际文件名.
     * @param path     指定根目录
     * @param absFileName 相对路径名，来自于ZipEntry中的name
     * @return java.io.File 实际的文件
     */
    public static File getRealFileName(String path, String absFileName) {
        String[] dirs = absFileName.split("/", absFileName.length());
        File ret = new File(path);// 创建文件对象
        if (dirs.length > 1) {
            for (int i = 0; i < dirs.length - 1; i++) {
                ret = new File(ret, dirs[i]);
            }
        }
        if (!ret.exists()) {         // 检测文件是否存在
            ret.mkdirs(); // 创建此抽象路径名指定的目录
        }
        ret = new File(ret, dirs[dirs.length - 1]);// 根据 ret 抽象路径名和 child 路径名字符串创建一个新 File 实例

        return ret;
    }

    public static void deleteFile(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFile(files[i]);
                }
            }
        }
        file.delete();
    }

    /**
     * 把一个文件转化为byte字节数组。
     */
    public static byte[] fileConvertToByteArray(String filePath) {
        byte[] data = null;

        try {
            FileInputStream fis = new FileInputStream(filePath);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            int len;
            byte[] buffer = new byte[1024];
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }

            data = baos.toByteArray();

            fis.close();
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public static  void inputStreamToFile(InputStream ins,File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * FileItem类对象创建
     *
     * @param inputStream inputStream
     * @param fileName    fileName
     * @return FileItem
     */
    public static FileItem createFileItem(InputStream inputStream, String fileName) {
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        String textFieldName = "file";
        FileItem item = factory.createItem(textFieldName, MediaType.MULTIPART_FORM_DATA_VALUE, true, fileName);
        int bytesRead = 0;
        byte[] buffer = new byte[10 * 1024 * 1024];
        OutputStream os = null;
        //使用输出流输出输入流的字节
        try {
            os = item.getOutputStream();
            while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            inputStream.close();
        } catch (IOException e) {
            log.error("Stream copy exception", e);
            throw new IllegalArgumentException("文件上传失败");
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    log.error("Stream close exception", e);

                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("Stream close exception", e);
                }
            }
        }

        return item;
    }

    /**
     * 判断文件大小
     * @param len
     * 文件长度
     * @param size
     *  限制大小
     * @param unit
     * 限制单位（B,K,M,G）
     * @return
     */
    public static boolean checkFileSize(Long len, int size, String unit) {
        double fileSize = 0;
        if ("B".equals(unit.toUpperCase())) {
            fileSize = (double) len;
        } else if ("K".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1024;
        } else if ("M".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1048576;
        } else if ("G".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1073741824;
        }
        if (fileSize > size) {
            return false;
        }
        return true;
    }

    /**
     * @Author yaoqi
     * @Description //TODO 获取文件大小
     * @Date 2022/5/25 11:17
     * @Param [len, unit]
     * @return double
     **/
    public static double getFileSize(Long len, String unit) {
        double fileSize = 0;
        if ("B".equals(unit.toUpperCase())) {
            fileSize = (double) len;
        } else if ("K".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1024;
        } else if ("M".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1048576;
        } else if ("G".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1073741824;
        }

        return fileSize;
    }

    /**
     * 获得控制台用户输入的信息
     * @return
     * @throws IOException
     */
    public static String getInputMessage() throws IOException{
        System.out.println("请输入您的命令∶");
        byte buffer[]=new byte[1024];
        int count= 0;
        try {
            count = System.in.read(buffer);
            char[] ch=new char[count-2];//最后两位为结束符，删去不要
            for(int i=0;i<count-2;i++)
                ch[i]=(char)buffer[i];
            String str=new String(ch);
            return str;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *以流的方式复制文件
     * @param src
     * @param dest
     * @throws IOException
     */
    public void copyFile(String src,String dest) throws IOException{
        FileInputStream in=new FileInputStream(src);
        File file=new File(dest);
        if(!file.exists()){
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        FileOutputStream out=new FileOutputStream(file);
        int c;
        byte buffer[]=new byte[1024];
        while((c=in.read(buffer))!=-1){
            for(int i=0;i<c;i++)
                out.write(buffer[i]);
        }
        in.close();
        out.close();
    }

    /**
     * 利用PrintStream写文件
     * @param path
     * @param content
     */
    public static void PrintStreamWriter(String path,String content){
        try {
            //"D:/test.txt"
            FileOutputStream out=new FileOutputStream(path);
            PrintStream p=new PrintStream(out);
            for(int i=0;i<content.length();i++)
//                p.println(content.charAt(i));
                p.print(content.charAt(i));
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * 利用StringBuffer写文件
     * @param path
     * @param content
     */
    public static void StringBufferWriter(String path,String content) throws IOException{
        File file=new File(path);
        if(!file.exists()){
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        FileOutputStream out=new FileOutputStream(file,false);
        StringBuffer sb=new StringBuffer();
        if(StringUtils.isNotBlank(content)){
            sb.append(content);
            out.write(sb.toString().getBytes("utf-8"));
        }
        out.close();
    }

    /**
     * 文件重命名
     */
    public static void renameFile(String path,String oldname,String newname){
        if(!oldname.equals(newname)){//新的文件名和以前文件名不同时,才有必要进行重命名
            File oldfile=new File(path+"/"+oldname);
            File newfile=new File(path+"/"+newname);
            if(newfile.exists())//若在该目录下已经有一个文件和新文件名相同，则不允许重命名
                log.info(newname+"已经存在！");
            else{
                oldfile.renameTo(newfile);
            }
        }
    }

    /**
     * 文件转移目录
     * 转移文件目录不等同于复制文件，
     * 复制文件是复制后两个目录都存在该文件，而转移文件目录则是转移后，只有新目录中存在该文件
     * @param filename
     * @param oldpath
     * @param newpath
     * @param cover
     * @return
     */
    public String changeDirectory(String filename, String oldpath, String newpath, boolean cover) throws IOException {
        if(!oldpath.equals(newpath)){
            File oldfile=new File(oldpath+"/"+filename);
            File newfile=new File(newpath+"/"+filename);
            if(newfile.exists()){//若在待转移目录下，已经存在待转移文件
                if(cover) {//覆盖
                    oldfile.renameTo(newfile);
                }
                else{
                    System.out.println("在新目录下已经存在："+filename);
                }
            }
         else{
                oldfile.renameTo(newfile);
            }
        }
        return null;
    }



    /**
     * 利用FileInputStream读取文件
     * @param path
     * @return
     * @throws IOException
     */
        public static String FileInputStreamDemo(String path) throws IOException{
        File file=new File(path);
        if(!file.exists()||file.isDirectory())
            throw new FileNotFoundException();
        FileInputStream fis=new FileInputStream(file);
        byte[] buf = new byte[1024];
        StringBuffer sb=new StringBuffer();
        while((fis.read(buf))!=-1){
            sb.append(new String(buf));
            buf=new byte[1024];//重新生成，避免和上次读取的数据重复
        }
        return sb.toString();
    }

    /**
     * 利用bufferReader读取文件
     * @param path
     * @return
     * @throws IOException
     */
    public static String BufferedReaderDemo(String path) throws IOException{
        File file=new File(path);
        if(!file.exists()||file.isDirectory())
            throw new FileNotFoundException();
        BufferedReader br=new BufferedReader(new FileReader(file));
        String temp=null;
        StringBuffer sb=new StringBuffer();
        temp=br.readLine();
        while(temp!=null){
            sb.append(temp+" ");
            temp=br.readLine();
        }
        return sb.toString();
    }

    /**
     * @Author yaoqi
     * @Description //TODO 读取文件行数
     * @Date 2022/5/25 11:19
     * @Param [path]
     * @return java.lang.Integer
     **/
    public static int readFileLine(File file) throws IOException{

        if(!file.exists()||file.isDirectory())
            throw new FileNotFoundException();

        BufferedReader bufferedReader=new BufferedReader(new FileReader(file));

        String line=null;
        int totalLineNum = 0;
        while (StringUtils.isNotBlank(line = bufferedReader.readLine())) {
            totalLineNum++;
        }
        bufferedReader.close();
        return totalLineNum;
    }

    /**
     * @Author yaoqi
     * @Description multipartFile
     * @Date 2022/6/17 16:16
     * @Param [multipartFile]
     * @return int
     **/
    public static int readMultipartFileLine(MultipartFile multipartFile) throws IOException{


        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(multipartFile.getInputStream(),"UTF8"));

        String line=null;
        int totalLineNum = 0;
        while (StringUtils.isNotBlank(line = bufferedReader.readLine())) {
            totalLineNum++;
        }
        bufferedReader.close();
        return totalLineNum;
    }

    /**
     * 利用dom4j读取xml文件
     * @param path
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    public static Document readXml(String path) throws DocumentException, IOException{
        File file=new File(path);
        if(!file.exists()){
            return null;
        }
        BufferedReader bufferedreader = new BufferedReader(new FileReader(file));
        SAXReader saxreader = new SAXReader();
        Document document = (Document)saxreader.read(bufferedreader);
        bufferedreader.close();
        return document;
    }

    /**
     * 创建文件
     * @param path
     * @return
     * @throws IOException
     */
    public static File createDirFile(String path) throws IOException {
        File dir=new File(path);
        if(!dir.exists()){
                if(dir.getParent() !=null){
                    dir.getParentFile().mkdirs();
                    dir.createNewFile();
                }else{
                    dir.createNewFile();
                }

        }
        return dir;
    }

    /**
     * 创建文件夹
     * @param path
     * @return
     * @throws IOException
     */
    public static File createDir(String path) throws IOException {
        File dir=new File(path);
        if(!dir.exists()){
            dir.mkdirs();
        }
        return dir;
    }

    /**
     * 删除文件
     * @param path 包含文件名
     */
    public static void delFile(String path){
        File file=new File(path);
        if(file.exists()&&file.isFile())
            file.delete();
    }

    /**
     * 删除文件夹
     * @param folderPath 文件夹完整绝对路径 ,"Z:/xuyun/save"
     */
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); //删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete(); //删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除指定文件夹下所有文件
     * @param path 文件夹完整绝对路径 ,"Z:/xuyun/save"
     */
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }

        if(file.isDirectory()){
            delFolder(file.getPath());
        }

        return flag;
    }

    public static Boolean deleteFileAndDir(File file) {

        //判断文件不为null或文件目录存在
        if (file == null || !file.exists()) {
            log.info("文件删除失败,请检查文件是否存在以及文件路径是否正确");
            return false;
        }
        //获取目录下子文件
        File[] files = file.listFiles();
        //遍历该目录下的文件对象
        for (File f : files) {
            //判断子目录是否存在子目录,如果是文件则删除
            if (f.isDirectory()) {
                //递归删除目录下的文件
                deleteFileAndDir(f);
            } else {
                //文件删除
                f.delete();
                //打印文件名
                log.info("文件名{}删除成功",f.getName());
            }
        }
        //文件夹删除
        file.delete();
        log.info("目录名{}删除成功", file.getName());
        return true;
    }

    public static HttpServletResponse download(ClassPathResource resource, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
//            File file = new File(path);
            File file = resource.getFile();
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(resource.getInputStream());
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }

    public static HttpServletResponse downloadLocalFile(File file, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
//            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }


    /** 读取txt文本信息 */
    public static String readTxtInfo(MultipartFile file, String outputFilePath, String outputFileName) throws Exception{


        String outputFileUrl = outputFilePath + outputFileName;

        File file1 = new File(outputFilePath+ outputFileName);
        if(!file1.getParentFile().exists()){
            file1.getParentFile().mkdirs();

            // 只能在一层目录下创建文件，不能跳级创建。先创建目录再创建文件夹
            boolean newFile = file1.createNewFile();
            log.info("创建文件成功!");
        }
        String inputFileEncode = getEncodeByFile(multiToFile(file));
        log.info("inputFileEncode===========" + inputFileEncode);
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), inputFileEncode));
        BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(outputFileUrl), "UTF-8"));
        String line;
        while (StringUtils.isNotBlank(line = bufferedReader.readLine())) {
            bufferedWriter.write(line + "\r\n");
        }
        bufferedWriter.close();
        bufferedReader.close();

        return outputFileUrl;

    }

    /**
     *
     * @param file 待读取的文件
     * @return
     * @throws Exception
     */
    public static List<String> readTxtLineInfo(File file) throws Exception{

        List<String> contentList = new ArrayList<>();
        //String inputFileEncode = getEncodeByFile(file);

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), "UTF-8"));

        String line;
        while (StringUtils.isNotBlank(line = bufferedReader.readLine())) {
            contentList.add(line);
        }
        bufferedReader.close();

        return contentList;

    }


    public static String readFileTxtInfo(File file, Integer type){

        BufferedReader br = null;
        StringBuilder stringBuilder = new StringBuilder();
        String newline = System.getProperty("line.separator");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
//            InputStream inputStream = file.getInputStream();
//            String fileEncode = getFileEncode(file);
            String fileEncode = getEncodeByFile(file);
            log.info("文件的编码为:{}",fileEncode);
            if(fileEncode.equals("windows-1252")){
                fileEncode = "Unicode";
            }

            if(type == 1){  // 说明是生成的文件，编码设置成UTF8
                fileEncode = "UTF-8";
            }
            br = new BufferedReader(new InputStreamReader(fileInputStream,fileEncode));
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
                String lineContent = lineTxt.trim();
                stringBuilder.append(lineContent).append(newline);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br !=null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

//    /**
//     * 利用第三方开源包cpdetector获取文件编码格式
//     *
//     * @param file
//     *            要判断文件编码格式的源文件的路径
//     * @author huanglei
//     * @version 2012-7-12 14:05
//     */
//    public static String getFileEncode(File file) {
//        /*
//         * detector是探测器，它把探测任务交给具体的探测实现类的实例完成。
//         * cpDetector内置了一些常用的探测实现类，这些探测实现类的实例可以通过add方法 加进来，如ParsingDetector、
//         * JChardetFacade、ASCIIDetector、UnicodeDetector。
//         * detector按照“谁最先返回非空的探测结果，就以该结果为准”的原则返回探测到的
//         * 字符集编码。使用需要用到三个第三方JAR包：antlr.jar、chardet.jar和cpdetector.jar
//         * cpDetector是基于统计学原理的，不保证完全正确。
//         */
//        CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
//        /*
//         * ParsingDetector可用于检查HTML、XML等文件或字符流的编码,构造方法中的参数用于
//         * 指示是否显示探测过程的详细信息，为false不显示。
//         */
//        detector.add(new ParsingDetector(false));
//        /*
//         * JChardetFacade封装了由Mozilla组织提供的JChardet，它可以完成大多数文件的编码
//         * 测定。所以，一般有了这个探测器就可满足大多数项目的要求，如果你还不放心，可以
//         * 再多加几个探测器，比如下面的ASCIIDetector、UnicodeDetector等。
//         */
//        detector.add(JChardetFacade.getInstance());// 用到antlr.jar、chardet.jar
//        // ASCIIDetector用于ASCII编码测定
//        detector.add(ASCIIDetector.getInstance());
//        // UnicodeDetector用于Unicode家族编码的测定
//        detector.add(UnicodeDetector.getInstance());
//        java.nio.charset.Charset charset = null;
////        File f = new File(path);
//        try {
//            charset = detector.detectCodepage(file.toURI().toURL());
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        if (charset != null)
//            return charset.name();
//        else
//            return null;
//    }

    /**
     * 获取文件的编码格式
     * @param file
     * @return
     */
    private static String getEncodeByFile(File file){

        String charset = "GBK";
        byte[] first3Bytes = new byte[3];
        try {
            boolean checked = false;
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            bis.mark(0);
            int read = bis.read(first3Bytes, 0, 3);
            if (read == -1) {
                return charset; //文件编码为 ANSI
            } else if (first3Bytes[0] == (byte) 0xFF
                    && first3Bytes[1] == (byte) 0xFE) {
                charset = "UTF-16LE"; //文件编码为 Unicode
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xFE
                    && first3Bytes[1] == (byte) 0xFF) {
                charset = "UTF-16BE"; //文件编码为 Unicode big endian
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xEF
                    && first3Bytes[1] == (byte) 0xBB
                    && first3Bytes[2] == (byte) 0xBF) {
                charset = "UTF-8"; //文件编码为 UTF-8
                checked = true;
            }
            bis.reset();
            if (!checked) {
                int loc = 0;
                while ((read = bis.read()) != -1) {
                    loc++;
                    if (read >= 0xF0)
                        break;
                    if (0x80 <= read && read <= 0xBF) // 单独出现BF以下的，也算是GBK
                        break;
                    if (0xC0 <= read && read <= 0xDF) {
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) // 双字节 (0xC0 - 0xDF)
                            // (0x80
                            // - 0xBF),也可能在GB编码内
                            continue;
                        else
                            break;
                    } else if (0xE0 <= read && read <= 0xEF) {// 也有可能出错，但是几率较小
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) {
                            read = bis.read();
                            if (0x80 <= read && read <= 0xBF) {
                                charset = "UTF-8";
                                break;
                            } else
                                break;
                        } else
                            break;
                    }
                }
            }
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return charset;
    }

    /**
     * 将内容逐行写入指定path路径下的文件
     * @param path
     */
    public static void writeContentToFileInOneLine(List<String> fileContentList, String path){

        BufferedWriter bw = null;

        try {

            File dirFile = FileUtils.createDirFile(path);
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dirFile, true), StandardCharsets.UTF_8));

            for(String lineContent:fileContentList){
                bw.write(lineContent);
                bw.newLine();
            }
            bw.flush();
        }catch (Exception ex){
            ex.printStackTrace();
            log.error("写入失败");
        }finally {
            try {
                if(bw !=null){

                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeContentToTxtInOneLine(List<String> fileContent, String path){

        BufferedWriter bw = null;

        try {

            File dirFile = FileUtils.createDirFile(path);
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dirFile, true), StandardCharsets.UTF_8));

            for(String lineContent:fileContent){
                bw.write(lineContent);
                bw.newLine();
            }
            bw.flush();
        }catch (Exception ex){
            ex.printStackTrace();
            log.error("写入失败");
        }finally {
            try {
                if(bw !=null){

                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * todo  删除本地临时文件
     * @param file
     * @return
     * @throws Exception
     */
    public static File multipartFileToFile(MultipartFile file) throws Exception {

        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            try {
                InputStream ins = null;
                ins = file.getInputStream();
                toFile = new File(file.getOriginalFilename());
                multipartFileInputStreamToFile(ins, toFile);
                ins.close();
            } finally {

//                delteTempFile(toFile);
            }
        }
        return toFile;
    }

    /**
     * MultipartFile转file
     * @param multipartFile
     * @return
     */
    public static File multiToFile(MultipartFile multipartFile) {
        //选择用缓冲区来实现这个转换即使用java 创建的临时文件 使用 MultipartFile.transferto()方法 。
        File file = null;
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String[] filename = originalFilename.split("\\.");
            file=File.createTempFile(filename[0], filename[1]);
            multipartFile.transferTo(file);
            file.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return file;
    }

    /**
     * 读取txt,csv文件
     *
     * @return
     */
    public static List<List<String>> readTxtOrCsvFile(InputStream input, int rowIndex) {

        List<List<String>> data = Lists.newArrayList();
        if (input == null) {
            return data;
        }
        InputStreamReader read = null;
        BufferedReader br = null;
        BufferedInputStream bb = null;
        try {
            bb = new BufferedInputStream(input);
            read = new InputStreamReader(bb, getCharSet(bb));
            br = new BufferedReader(read);
            String line;
            while (StringUtils.isNotBlank(line = br.readLine())) {
                if (StringUtils.isNotBlank(line)) {
                    List<String> dd = Arrays.asList(line.split(","));
                    List<String> n = new ArrayList<>();
                    for (int i = 0; i < dd.size(); i++) {
                        String cellData = dd.get(i);
                        if(cellData.endsWith(".0") && isNumber(cellData.substring(0,cellData.lastIndexOf(".0")))){
                            cellData = cellData.substring(0,cellData.lastIndexOf(".0"));
                        }
                        n.add(cellData);
                    }
                    data.add(n);
                    if(rowIndex == 0){
                        return data;
                    }
                }else {
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (read != null) {
                    read.close();
                }
                if (bb != null) {
                    bb.close();
                }
                if (input != null) {
                    input.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    // 根据阿里巴巴代码规范，将Pattern设置为全局常量
    // 通过 -?[0-9]+(\\\\.[0-9]+)? 进行匹配是否为数字
    private static Pattern pattern = Pattern.compile("-?[0-9]+(\\\\\\\\.[0-9]+)?");

    /**
     * 通过正则表达式判断字符串是否为数字
     * @param str
     * @return
     */
    private static boolean isNumber(String str) {
        // 通过Matcher进行字符串匹配
        Matcher m = pattern.matcher(str);
        // 如果正则匹配通过 m.matches() 方法返回 true ，反之 false
        return m.matches();
    }

    /**
     * 获取流对应的编码类型
     * @param bb
     * @return
     * @throws Exception
     */
    private static String getCharSet(BufferedInputStream bb) throws Exception {

        String charSet = null;
        byte[] buffer = new byte[3];
        //因流读取后再读取可能会缺少内容，此处需要先读，然后再还原
        bb.mark(bb.available() + 1);
        bb.read(buffer);
        bb.reset();
        String s = Integer.toHexString(buffer[0] & 0xFF) + Integer.toHexString(buffer[1] & 0xFF) + Integer.toHexString(buffer[2] & 0xFF);
        switch (s) {
            //GBK,GB2312对应均为d5cbba，统一当成GB2312解析
            case "d5cbba":
                charSet = "GB2312";
                break;
            case "efbbbf":
                charSet = "UTF-8";
                break;
            default:
                charSet = "GB2312";
                break;
        }

        return charSet;
    }


    //获取流文件
    private static void multipartFileInputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<File> getFileList(String strPath) {

        List<File> filelist = new ArrayList<>();

        File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
                } else  { // 判断文件名
                    String strFileName = files[i].getAbsolutePath();
//                    System.out.println("---" + strFileName);
                    filelist.add(files[i]);
                }
            }

        }
        return filelist;
    }

    /**
     * 将JSON数据格式化并保存到文件中
     * @param jsonData 需要输出的json数
     * @param filePath 输出的文件地址
     * @return
     */
    public static boolean createJsonFile(Object jsonData, String filePath) {
        String content = JSON.toJSONString(jsonData, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat);
        // 标记文件生成是否成功
        boolean flag = true;
        // 生成json格式文件
        try {
            // 保证创建一个新文件
            File file = new File(filePath);
            if (!file.getParentFile().exists()) { // 如果父目录不存在，创建父目录
                file.getParentFile().mkdirs();
            }
            if (file.exists()) { // 如果已存在,删除旧文件
                file.delete();
            }
            file.createNewFile();
            // 将格式化后的字符串写入文件
            Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            write.write(content);
            write.flush();
            write.close();
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 将文本统计信息的map写入本地临时文件
     * @param value
     * @param roleFilePath
     */
    public static void writeMapDataToTempFile(Map<String, Integer> value, String roleFilePath) {

        BufferedWriter bw = null;

        try {

            File dirFile = FileUtils.createDirFile(roleFilePath);
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dirFile, true), StandardCharsets.UTF_8));

            Iterator<Map.Entry<String, Integer>> iterator = value.entrySet().iterator();
            while (iterator.hasNext()){

                Map.Entry<String, Integer> next = iterator.next();
                Integer countNum = next.getValue();
                String text = next.getKey();
                String tempContent =countNum+"\t"+text;
                bw.write(tempContent);
                bw.newLine();
            }
            bw.flush();
        }catch (Exception ex){
            ex.printStackTrace();
            log.error("写入失败");
        }finally {
            try {
                if(bw !=null){

                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

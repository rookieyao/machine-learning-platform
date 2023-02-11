package com.pzj.project.common.minio;

import com.pzj.project.common.util.FileUtils;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.Item;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName MinIOService
 * @Description
 * @Author yaoqi
 * @Date 2022/6/9 17:35
 * @Version 1.0
 **/
@Component
@EnableConfigurationProperties({MinIOProperties.class})
public class MinIOService implements InitializingBean {

    Logger log = LoggerFactory.getLogger(MinIOService.class);

    private MinIOProperties minIOProperties;

    public MinIOService(MinIOProperties minIOProperties){
        this.minIOProperties=minIOProperties;
    }

    private MinioClient client;

    @Override
    public void afterPropertiesSet() {

        Assert.notNull(minIOProperties.getEndpoint(), "MinIO URL 为空");
        Assert.notNull(minIOProperties.getAccessKey(), "MinIO accessKey为空");
        Assert.notNull(minIOProperties.getSecretKey(), "MinIO secretKey为空");

        minIOProperties.setUrl(minIOProperties.getEndpoint()+":"+minIOProperties.getPort()+"/");

        this.client = new MinioClient.Builder()
                .credentials(minIOProperties.getAccessKey(), minIOProperties.getSecretKey())
                .endpoint(minIOProperties.getEndpoint(),minIOProperties.getPort(),minIOProperties.getSecure())
                .build();
    }

    /**
     * 检查存储桶是否存在
     *
     * @param bucketName 存储桶名称
     * @return
     */
    @SneakyThrows
    public boolean bucketExists(String bucketName) {
        boolean found = client.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (found) {
            log.info("{}:exist",bucketName);
        } else {
            log.info("{}:does not exist",bucketName);
        }
        return found;
    }

    @SneakyThrows
    public void createBucketIfAbsent(String bucketName) {
        log.info("{}是否存在:{}",bucketName,bucketExists(bucketName));

        if (!bucketExists(bucketName)) {
            MakeBucketArgs makeBucketArgs = MakeBucketArgs.builder()
                    .bucket(bucketName).build();
            client.makeBucket(makeBucketArgs);
            //设置桶的policy策略
            client.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucketName).config(MinioPolicyConstant.READ_WRITE.replace(MinioPolicyConstant.BUCKET_PARAM,
                    bucketName)).build());
            log.info("创建成功");
        }
    }

    /**
     * @Author yaoqi
     * @Description 上传文件
     * @Date 2022/6/17 16:21
     * @Param [objectName, file]
     * @return java.lang.String
     **/
    public String uploadMultipartFile(String objectName, MultipartFile file) throws Exception {

        String bucketName = minIOProperties.getBucketName();
        createBucketIfAbsent(bucketName);

        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .contentType(MediaType.ALL_VALUE)

                .stream(file.getInputStream(), file.getInputStream().available(), -1)
                .build();

        client.putObject(putObjectArgs);
        return objectName;
//        return minIOProperties.getUrl() + bucketName + "/" + objectName;
    }

    public String uploadFile(String objectName, File file) throws Exception {

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);

            String bucketName = minIOProperties.getBucketName();
            createBucketIfAbsent(bucketName);

            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .contentType(MediaType.ALL_VALUE)

                    .stream(fileInputStream, fileInputStream.available(), -1)
                    .build();

            client.putObject(putObjectArgs);
        } finally {
            if(fileInputStream !=null){
                fileInputStream.close();
            }
        }
        return objectName;
    }

    /**
     * todo 这个方法可以重写，不返回流，直接将文件下载至指定路径就好
     * @param bucketName
     * @param fileName
     * @return
     * @throws Exception
     */
    public InputStream download(String bucketName, String fileName) throws Exception {

        try {
            return client.getObject(GetObjectArgs
                    .builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("文件下载失败");
        }
    }

    public void download(String bucketName, String fileName, HttpServletResponse response) throws Exception{

        String substring = fileName.substring(fileName.lastIndexOf("/")+1);

        String encodeFileName = new String(substring.getBytes("UTF-8"), "iso8859-1");

        InputStream objectInputStream = client.getObject(GetObjectArgs
                .builder()
                .bucket(bucketName)
                .object(fileName)
                .build());

        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=" + encodeFileName);
        response.setContentType("application/octet-stream");
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = objectInputStream.read(b)) > 0){
                response.getOutputStream().write(b, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> getDataFilePathList(String directoryPath) {

        try {

            return getListObjects(directoryPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void removeObject(String bucketName, String objectName) throws Exception {
        RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build();
        client.removeObject(removeObjectArgs);
    }

    /**
     * 获取文件外链
     * 文件预签名地址
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param method 生成外链 使用GET， 生成预签名 使用PUT
     * @return url
     */
    @SneakyThrows
    public String getObjectURL(String bucketName, String objectName, Method method) {

        if(objectName.startsWith("/")){
            objectName = objectName.substring(1);
        }
        GetPresignedObjectUrlArgs build = GetPresignedObjectUrlArgs.builder()
                .method(method)
                .bucket(bucketName)
                .object(objectName)
                //.expiry(60 * 60 * 24 * 365 * 10)
                .build();;

        return client.getPresignedObjectUrl(build);
    }

    /**
     * 获取文件信息, 如果抛出异常则说明文件不存在
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     */
    public ObjectStat statObject(String bucketName, String objectName)
            throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, InvalidBucketNameException, ErrorResponseException {
        return client.statObject(StatObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }

    /**
     * 查询指定路径下所有文件
     * @param prefix
     * @return
     * @throws Exception
     */
    public List<String> getListObjects(String prefix) throws Exception{

        List<String> filePath = new ArrayList<>();
        Iterable<Result<Item>> results = client.listObjects(
                ListObjectsArgs.builder()
                        .bucket(minIOProperties.getBucketName())
//                        .startAfter("ExampleGuide.pdf")
                        .prefix(prefix)
//                        .maxKeys(100)
//                        .includeVersions(true)
                        .build());
        Iterator<Result<Item>> iterator = results.iterator();
        while (iterator.hasNext()){
            Result<Item> next = iterator.next();
            Item item = next.get();
            log.info("===========,{}",item.objectName());
            if(item.isDir()){ //
                filePath.addAll(getListObjects(item.objectName()));
            }else {
                filePath.add(item.objectName());
            }
        }

        return filePath;
    }

    /**
     * @Author yaoqi
     * @Description 将minIo的fromPath下的文件为fromFileName的文件拷贝到minIo的toDirPath目录下
     * @Date 2022/9/8 11:48
     * @Param [fromPath, toPath, fromPrefix]
     * @return java.util.List<java.lang.String>
     **/
    public boolean minIoCopyFile(String fromDirPath, String toDirPath,List<String> fromFileNameList) throws Exception{


        List<String> toBeCopiedFileList = new ArrayList<>();
        // 1. 获取minIo上的目标文件
        Iterable<Result<Item>> results = client.listObjects(
                ListObjectsArgs.builder()
                        .bucket(minIOProperties.getBucketName())
                        .prefix(fromDirPath)
                        .build());
        Iterator<Result<Item>> iterator = results.iterator();
        while (iterator.hasNext()){
            Result<Item> next = iterator.next();
            Item item = next.get();
            if(!item.isDir()){
                String objectName = item.objectName();
                String fileName = objectName.substring(objectName.lastIndexOf("/")+1);
                if(fromFileNameList.contains(fileName)){
                    toBeCopiedFileList.add(objectName);
                }
            }
        }

        if(toBeCopiedFileList.size() == 0){
            log.info("toBeCopiedFileList`s size is 0,not to move file!");
        }

        // 2. 将目标文件下载到本地
        List<File> localFileList = new ArrayList<>();
        toBeCopiedFileList.stream().forEach(objectName ->{

            try {
                InputStream download = download(minIOProperties.getBucketName(), objectName);
                File dirFile = FileUtils.createDirFile(objectName);
                FileUtils.inputStreamToFile(download, dirFile);
                localFileList.add(dirFile);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("download file error:"+e.getMessage());
            }
        });

        // 3. 将文件上传至toDirPath
        localFileList.stream().forEach(file -> {
            try {
                String name = file.getName();
                String objectName = toDirPath+"/"+name;
                uploadFile(objectName, file);

                // 该文件上传之后，将本地文件删除
                file.delete();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("download file error:"+e.getMessage());
            }
        });


        return true;
    }

}

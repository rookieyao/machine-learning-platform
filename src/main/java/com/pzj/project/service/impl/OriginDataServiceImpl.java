package com.pzj.project.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pzj.project.common.minio.MinIOService;
import com.pzj.project.dto.OriginDataDTO;
import com.pzj.project.entity.OriginAppendDataFile;
import com.pzj.project.mapper.OriginAppendDataFileMapper;
import com.pzj.project.mapper.OriginDataMapper;
import com.pzj.project.entity.OriginData;
import com.pzj.project.model.OriginDataModel;
import com.pzj.project.service.OriginDataService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * (OriginData)�����ʵ����
 *
 * @author makejava
 * @since 2023-02-18 12:11:12
 */
@Service
public class OriginDataServiceImpl extends ServiceImpl<OriginDataMapper, OriginData> implements OriginDataService {


    @Resource
    private OriginDataMapper originDataMapper;

    @Resource
    private OriginAppendDataFileMapper originAppendDataMapper;


    
    /**
     * ����idsɾ����������
     **/
    @Override
    public void deleteByIds(String ids) {
        String[] idsArr = ids.split(",");
        if (idsArr.length > 0) {
            List<Long> idList = Stream.of(idsArr).map(Long::valueOf).collect(Collectors.toList());
            originDataMapper.deleteBatchIds(idList);
        }
    }

    /**
     * 查找原始数据名称
     * @return
     */
    @Override
    public ArrayList<OriginDataModel> getByOriginData(OriginDataDTO originDataDTO) {
        Integer result_pageNo = originDataDTO.getPageSize();
        Integer result_pageSize = originDataDTO.getPageSize();
        Integer result = (result_pageNo-1)*result_pageSize;
        originDataDTO.setPageNum(result);
        return originDataMapper.getByOriginData(originDataDTO);
    }

//    /**
//     * 删除信息
//     */
//    @Override
//    public int delById(Long id) {
//        return originDataMapper.delById(id);
//    }

    /**
     * 新增信息
     */
//    @Override
//    public int insertData(OriginData originData, MultipartFile[] multipartFiles){
//        String res = null;
//        //定义文件变量
//        File file = null;
//        try{
//            //循环存入文件
//            Arrays.stream(multipartFiles).forEach(multipartFile -> {
//                try {
//                    //获取文件名称
//                    String fileName = multipartFile.getOriginalFilename();
//                    //获取随机文件名
////                    String new_fileName = LocalObjectUuidHelper.generateLocalObjectUuid().replace("-","") +
////                            fileName.substring(fileName.lastIndexOf("."));
//                    //minIOService保存文件
////                    minIOService.uploadMultipartFile("/file/" + fileName,multipartFile);
//                    System.out.println(fileName);
//                    OriginAppendDataFile originAppendData = new OriginAppendDataFile();
//                    originAppendData.setOriginDataID(originData.getId());
//                    originAppendData.setOriginAppendFilePath("/" + fileName);
//                    originAppendDataMapper.insert(originAppendData);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            });
//        }catch (Exception e){
//            e.printStackTrace();
//            res = "上传失败";
//            System.out.println(res);
//        }
//        return originDataMapper.insert(originData);
//    }
}



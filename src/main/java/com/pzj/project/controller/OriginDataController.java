package com.pzj.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pzj.project.common.FileCondition;
import com.pzj.project.common.ResponseResult;
import com.pzj.project.common.Result;
import com.pzj.project.common.enums.OriginDataProcessStateEnum;
import com.pzj.project.common.minio.MinIOService;
import com.pzj.project.dto.OriginDataDTO;
import com.pzj.project.entity.OriginAppendDataFile;
import com.pzj.project.entity.OriginData;
import com.pzj.project.entity.TrainData;
import com.pzj.project.service.OriginAppendDataFileService;
import com.pzj.project.service.OriginDataService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Insert;
import org.hibernate.boot.jaxb.Origin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author rookie
 * @Date 2023-02-08 20:51
 * @Description
 **/
@CrossOrigin
@RestController
@RequestMapping("/originData")
public class OriginDataController extends ResponseResult {
    @Resource
    private OriginDataService originDataService;

    @Resource
    private OriginAppendDataFileService originAppendDataFileService;

    @Resource
    MinIOService minIOService;

    @PostMapping("/listPage")
    public Result<?> getByOriginDataName(@RequestBody(required = false) OriginDataDTO originDataDTO) {

        PageHelper.startPage(originDataDTO.getPageNum(), originDataDTO.getPageSize());
        //������ѯ
        QueryWrapper<OriginData> queryWrapper = new QueryWrapper();

        String originDataName = originDataDTO.getOriginDataName();
        if (StringUtils.isNotBlank(originDataName)) {
            queryWrapper.lambda().eq(OriginData::getOriginDataName, originDataName);
        }

        Integer processState = originDataDTO.getProcessState();
        if (processState != null) {
            queryWrapper.lambda().eq(OriginData::getProcessState, processState.intValue());
        }

        //����
        // queryWrapper.lambda().orderByDesc(TrainData::getCreateTime);
        //ִ�в�ѯ
        List<OriginData> list = originDataService.list(queryWrapper);
        PageInfo<OriginData> pageInfo = new PageInfo<>(list);
        //��װ��ѯ���
        return Result.success(pageInfo);
    }

    @DeleteMapping("/delData/{id}")
    public Result<?> delById(@PathVariable("id") Long id) {
        try {
            OriginData byId = originDataService.getById(id);
            //当将字段更新为null时，字段值不会更新
//        byId.setIsDelete(1);
//        originDataService.updateById(byId);

            LambdaUpdateWrapper<OriginData> updateWrapper = new LambdaUpdateWrapper<>();
            //找到该id的数据
            updateWrapper.eq(OriginData::getId, id);
            //修改该id的is_delete值
            updateWrapper.set(OriginData::getIsDelete, 1);
            //提交修改
            originDataService.update(byId, updateWrapper);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
        return Result.success();
    }

    @PostMapping("/insetData")
    public Result<?> insertData(OriginData originData,
                                @RequestParam(value = "files") MultipartFile[] multipartFiles) {
        originData.setProcessState(OriginDataProcessStateEnum.STATE_ONE.getStateNum());
        originData.setOriginFilePath("/file");
        originDataService.save(originData);
        //originAppendDataFile新增
        String res = null;
        try {
            //循环存入文件
            Arrays.stream(multipartFiles).forEach(multipartFile -> {
                try {
                    //获取文件名称
                    String fileName = multipartFile.getOriginalFilename();
                    minIOService.uploadMultipartFile("/file/" + fileName, multipartFile);
                    //将数据存入origin
                    OriginAppendDataFile originAppendDataFile = new OriginAppendDataFile();
                    originAppendDataFile.setOriginDataId(originData.getId());
                    originAppendDataFile.setOriginAppendFilePath("/" + fileName);
                    originAppendDataFile.setOriginAppendFileSize(FileCondition.getFileSize(multipartFile));
                    originAppendDataFile.setLineNum(FileCondition.getFileLineNum(multipartFile));
                    originAppendDataFileService.save(originAppendDataFile);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            res = "上传失败";
            System.out.println(res);
        }
        return Result.success();
    }

}

package com.pzj.project.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pzj.project.common.Result;
import com.pzj.project.entity.ProjectServeVersionTrainModelVersion;
import com.pzj.project.dto.ProjectServeVersionTrainModelVersionDTO;
import com.pzj.project.service.ProjectServeVersionTrainModelVersionService;
import com.pzj.project.common.enums.ErrorCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (ProjectServeVersionTrainModelVersion)����Ʋ�
 *
 * @author makejava
 * @since 2023-02-18 20:40:53
 */
@RestController
@RequestMapping("/projectServeVersionTrainModelVersion")
public class ProjectServeVersionTrainModelVersionController {
    /**
     * �������
     */
    @Autowired
    private ProjectServeVersionTrainModelVersionService projectServeVersionTrainModelVersionService;

    /**
     * �б��ѯ����ҳ ��ѯȫ��
     **/
    @PostMapping("/listAll")
    public Result<?> listAll(@RequestBody ProjectServeVersionTrainModelVersionDTO projectServeVersionTrainModelVersionDTO) {
        //������ѯ
        QueryWrapper<ProjectServeVersionTrainModelVersion> queryWrapper = new QueryWrapper();
        //����
        // queryWrapper.lambda().orderByDesc(ProjectServeVersionTrainModelVersion::getCreateTime);
        //ִ�в�ѯ
        List<ProjectServeVersionTrainModelVersion> list = projectServeVersionTrainModelVersionService.list(queryWrapper);
        //��װ��ѯ���
        return Result.success(list);
    }
    
    /**
     * �б��ѯ ��ҳ
     * ֻ�ܲ�ѯ�Լ���
     **/
    @PostMapping("/listPage")
    public Result<?> listPage(@RequestBody ProjectServeVersionTrainModelVersionDTO projectServeVersionTrainModelVersionDTO) {
        
        PageHelper.startPage(projectServeVersionTrainModelVersionDTO.getPageNum(),projectServeVersionTrainModelVersionDTO.getPageSize());
        //������ѯ
        QueryWrapper<ProjectServeVersionTrainModelVersion> queryWrapper = new QueryWrapper();
        
        //����
        // queryWrapper.lambda().orderByDesc(ProjectServeVersionTrainModelVersion::getCreateTime);
        //ִ�в�ѯ
        List<ProjectServeVersionTrainModelVersion> list = projectServeVersionTrainModelVersionService.list(queryWrapper);
        PageInfo<ProjectServeVersionTrainModelVersion> pageInfo = new PageInfo<>(list);
        //��װ��ѯ���
        return Result.success(pageInfo);
    }
    
   /**
     * ����
     **/
    @PostMapping("/insert")
    public Result<?> insert(@RequestBody ProjectServeVersionTrainModelVersionDTO projectServeVersionTrainModelVersionDTO) {

                        //��װ��ѯ���
        return Result.success();
    }

    /**
     * ����id����
     **/
    @PostMapping("updateById")
    public Result<?> updateById(@RequestBody ProjectServeVersionTrainModelVersionDTO projectServeVersionTrainModelVersionDTO) throws Exception {
        //�ֶμ��
                        
               //��װ��ѯ���
        return Result.success();
    }


    /**
     * ����id��ѯ
     **/
    @GetMapping("getById/{id}")
    public Result<?> getById(@PathVariable("id") Long id) {
        ProjectServeVersionTrainModelVersion temp = projectServeVersionTrainModelVersionService.getById(id);
        //��װ��ѯ���
        return Result.success(temp);
    }

    /**
     * ����id����
     **/
    @PostMapping("deleteById/{id}")
    public Result<?> deleteById(@PathVariable("id") Long id) {
        projectServeVersionTrainModelVersionService.removeById(id);
        //��װ��ѯ���
        return Result.success();
    }

    /**
     * ���ݶ��idɾ��
     **/
    @PostMapping("deleteByIds")
    public Result<?> deleteByIds(@Param("ids") String ids) throws Exception {
        //ҵ����
        if (StringUtils.isEmpty(ids)) {
            return Result.error(ErrorCodeEnum.PARAM_ERROR);
        }
        projectServeVersionTrainModelVersionService.deleteByIds(ids);
        //��װ��ѯ���
        return Result.success();
    }
    
    
}



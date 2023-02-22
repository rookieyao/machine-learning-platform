package com.pzj.project.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pzj.project.common.Result;
import com.pzj.project.entity.ProjectServeVesion;
import com.pzj.project.dto.ProjectServeVesionDTO;
import com.pzj.project.service.ProjectServeVesionService;
import com.pzj.project.common.enums.ErrorCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (ProjectServeVesion)����Ʋ�
 *
 * @author makejava
 * @since 2023-02-18 20:40:54
 */
@RestController
@RequestMapping("/projectServeVesion")
public class ProjectServeVesionController {
    /**
     * �������
     */
    @Autowired
    private ProjectServeVesionService projectServeVesionService;

    /**
     * �б��ѯ����ҳ ��ѯȫ��
     **/
    @PostMapping("/listAll")
    public Result<?> listAll(@RequestBody ProjectServeVesionDTO projectServeVesionDTO) {
        //������ѯ
        QueryWrapper<ProjectServeVesion> queryWrapper = new QueryWrapper();
        //����
        // queryWrapper.lambda().orderByDesc(ProjectServeVesion::getCreateTime);
        //ִ�в�ѯ
        List<ProjectServeVesion> list = projectServeVesionService.list(queryWrapper);
        //��װ��ѯ���
        return Result.success(list);
    }
    
    /**
     * �б��ѯ ��ҳ
     * ֻ�ܲ�ѯ�Լ���
     **/
    @PostMapping("/listPage")
    public Result<?> listPage(@RequestBody ProjectServeVesionDTO projectServeVesionDTO) {
        
        PageHelper.startPage(projectServeVesionDTO.getPageNum(),projectServeVesionDTO.getPageSize());
        //������ѯ
        QueryWrapper<ProjectServeVesion> queryWrapper = new QueryWrapper();
        
        //����
        // queryWrapper.lambda().orderByDesc(ProjectServeVesion::getCreateTime);
        //ִ�в�ѯ
        List<ProjectServeVesion> list = projectServeVesionService.list(queryWrapper);
        PageInfo<ProjectServeVesion> pageInfo = new PageInfo<>(list);
        //��װ��ѯ���
        return Result.success(pageInfo);
    }
    
   /**
     * ����
     **/
    @PostMapping("/insert")
    public Result<?> insert(@RequestBody ProjectServeVesionDTO projectServeVesionDTO) {

                        //��װ��ѯ���
        return Result.success();
    }

    /**
     * ����id����
     **/
    @PostMapping("updateById")
    public Result<?> updateById(@RequestBody ProjectServeVesionDTO projectServeVesionDTO) throws Exception {
        //�ֶμ��
                        
               //��װ��ѯ���
        return Result.success();
    }


    /**
     * ����id��ѯ
     **/
    @GetMapping("getById/{id}")
    public Result<?> getById(@PathVariable("id") Long id) {
        ProjectServeVesion temp = projectServeVesionService.getById(id);
        //��װ��ѯ���
        return Result.success(temp);
    }

    /**
     * ����id����
     **/
    @PostMapping("deleteById/{id}")
    public Result<?> deleteById(@PathVariable("id") Long id) {
        projectServeVesionService.removeById(id);
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
        projectServeVesionService.deleteByIds(ids);
        //��װ��ѯ���
        return Result.success();
    }
    
    
}



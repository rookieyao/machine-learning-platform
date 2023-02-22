package com.pzj.project.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pzj.project.common.Result;
import com.pzj.project.entity.ProjectServe;
import com.pzj.project.dto.ProjectServeDTO;
import com.pzj.project.service.ProjectServeService;
import com.pzj.project.common.enums.ErrorCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (ProjectServe)����Ʋ�
 *
 * @author makejava
 * @since 2023-02-18 20:40:53
 */
@RestController
@RequestMapping("/projectServe")
public class ProjectServeController {
    /**
     * �������
     */
    @Autowired
    private ProjectServeService projectServeService;

    /**
     * �б��ѯ����ҳ ��ѯȫ��
     **/
    @PostMapping("/listAll")
    public Result<?> listAll(@RequestBody ProjectServeDTO projectServeDTO) {
        //������ѯ
        QueryWrapper<ProjectServe> queryWrapper = new QueryWrapper();
        //����
        // queryWrapper.lambda().orderByDesc(ProjectServe::getCreateTime);
        //ִ�в�ѯ
        List<ProjectServe> list = projectServeService.list(queryWrapper);
        //��װ��ѯ���
        return Result.success(list);
    }
    
    /**
     * �б��ѯ ��ҳ
     * ֻ�ܲ�ѯ�Լ���
     **/
    @PostMapping("/listPage")
    public Result<?> listPage(@RequestBody ProjectServeDTO projectServeDTO) {
        
        PageHelper.startPage(projectServeDTO.getPageNum(),projectServeDTO.getPageSize());
        //������ѯ
        QueryWrapper<ProjectServe> queryWrapper = new QueryWrapper();
        
        //����
        // queryWrapper.lambda().orderByDesc(ProjectServe::getCreateTime);
        //ִ�в�ѯ
        List<ProjectServe> list = projectServeService.list(queryWrapper);
        PageInfo<ProjectServe> pageInfo = new PageInfo<>(list);
        //��װ��ѯ���
        return Result.success(pageInfo);
    }
    
   /**
     * ����
     **/
    @PostMapping("/insert")
    public Result<?> insert(@RequestBody ProjectServeDTO projectServeDTO) {

                        //��װ��ѯ���
        return Result.success();
    }

    /**
     * ����id����
     **/
    @PostMapping("updateById")
    public Result<?> updateById(@RequestBody ProjectServeDTO projectServeDTO) throws Exception {
        //�ֶμ��
                        
               //��װ��ѯ���
        return Result.success();
    }


    /**
     * ����id��ѯ
     **/
    @GetMapping("getById/{id}")
    public Result<?> getById(@PathVariable("id") Long id) {
        ProjectServe temp = projectServeService.getById(id);
        //��װ��ѯ���
        return Result.success(temp);
    }

    /**
     * ����id����
     **/
    @PostMapping("deleteById/{id}")
    public Result<?> deleteById(@PathVariable("id") Long id) {
        projectServeService.removeById(id);
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
        projectServeService.deleteByIds(ids);
        //��װ��ѯ���
        return Result.success();
    }
    
    
}



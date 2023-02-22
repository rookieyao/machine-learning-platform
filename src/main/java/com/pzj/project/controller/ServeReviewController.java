package com.pzj.project.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pzj.project.common.Result;
import com.pzj.project.entity.ServeReview;
import com.pzj.project.dto.ServeReviewDTO;
import com.pzj.project.service.ServeReviewService;
import com.pzj.project.common.enums.ErrorCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (ServeReview)����Ʋ�
 *
 * @author makejava
 * @since 2023-02-18 20:40:54
 */
@RestController
@RequestMapping("/serveReview")
public class ServeReviewController {
    /**
     * �������
     */
    @Autowired
    private ServeReviewService serveReviewService;

    /**
     * �б��ѯ����ҳ ��ѯȫ��
     **/
    @PostMapping("/listAll")
    public Result<?> listAll(@RequestBody ServeReviewDTO serveReviewDTO) {
        //������ѯ
        QueryWrapper<ServeReview> queryWrapper = new QueryWrapper();
        //����
        // queryWrapper.lambda().orderByDesc(ServeReview::getCreateTime);
        //ִ�в�ѯ
        List<ServeReview> list = serveReviewService.list(queryWrapper);
        //��װ��ѯ���
        return Result.success(list);
    }
    
    /**
     * �б��ѯ ��ҳ
     * ֻ�ܲ�ѯ�Լ���
     **/
    @PostMapping("/listPage")
    public Result<?> listPage(@RequestBody ServeReviewDTO serveReviewDTO) {
        
        PageHelper.startPage(serveReviewDTO.getPageNum(),serveReviewDTO.getPageSize());
        //������ѯ
        QueryWrapper<ServeReview> queryWrapper = new QueryWrapper();
        
        //����
        // queryWrapper.lambda().orderByDesc(ServeReview::getCreateTime);
        //ִ�в�ѯ
        List<ServeReview> list = serveReviewService.list(queryWrapper);
        PageInfo<ServeReview> pageInfo = new PageInfo<>(list);
        //��װ��ѯ���
        return Result.success(pageInfo);
    }
    
   /**
     * ����
     **/
    @PostMapping("/insert")
    public Result<?> insert(@RequestBody ServeReviewDTO serveReviewDTO) {

                        //��װ��ѯ���
        return Result.success();
    }

    /**
     * ����id����
     **/
    @PostMapping("updateById")
    public Result<?> updateById(@RequestBody ServeReviewDTO serveReviewDTO) throws Exception {
        //�ֶμ��
                        
               //��װ��ѯ���
        return Result.success();
    }


    /**
     * ����id��ѯ
     **/
    @GetMapping("getById/{id}")
    public Result<?> getById(@PathVariable("id") Long id) {
        ServeReview temp = serveReviewService.getById(id);
        //��װ��ѯ���
        return Result.success(temp);
    }

    /**
     * ����id����
     **/
    @PostMapping("deleteById/{id}")
    public Result<?> deleteById(@PathVariable("id") Long id) {
        serveReviewService.removeById(id);
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
        serveReviewService.deleteByIds(ids);
        //��װ��ѯ���
        return Result.success();
    }
    
    
}



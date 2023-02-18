package com.pzj.project.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pzj.project.common.Result;
import com.pzj.project.entity.WordVector;
import com.pzj.project.dto.WordVectorDTO;
import com.pzj.project.service.WordVectorService;
import com.pzj.project.common.enums.ErrorCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (WordVector)����Ʋ�
 *
 * @author makejava
 * @since 2023-02-18 20:34:16
 */
@RestController
@RequestMapping("/wordVector")
public class WordVectorController {
    /**
     * �������
     */
    @Autowired
    private WordVectorService wordVectorService;

    /**
     * �б��ѯ����ҳ ��ѯȫ��
     **/
    @PostMapping("/listAll")
    public Result<?> listAll(@RequestBody WordVectorDTO wordVectorDTO) {
        //������ѯ
        QueryWrapper<WordVector> queryWrapper = new QueryWrapper();
        //����
        // queryWrapper.lambda().orderByDesc(WordVector::getCreateTime);
        //ִ�в�ѯ
        List<WordVector> list = wordVectorService.list(queryWrapper);
        //��װ��ѯ���
        return Result.success(list);
    }
    
    /**
     * �б��ѯ ��ҳ
     * ֻ�ܲ�ѯ�Լ���
     **/
    @PostMapping("/listPage")
    public Result<?> listPage(@RequestBody WordVectorDTO wordVectorDTO) {
        
        PageHelper.startPage(wordVectorDTO.getPageNum(),wordVectorDTO.getPageSize());
        //������ѯ
        QueryWrapper<WordVector> queryWrapper = new QueryWrapper();
        
        //����
        // queryWrapper.lambda().orderByDesc(WordVector::getCreateTime);
        //ִ�в�ѯ
        List<WordVector> list = wordVectorService.list(queryWrapper);
        PageInfo<WordVector> pageInfo = new PageInfo<>(list);
        //��װ��ѯ���
        return Result.success(pageInfo);
    }
    
   /**
     * ����
     **/
    @PostMapping("/insert")
    public Result<?> insert(@RequestBody WordVectorDTO wordVectorDTO) {

                        //��װ��ѯ���
        return Result.success();
    }

    /**
     * ����id����
     **/
    @PostMapping("updateById")
    public Result<?> updateById(@RequestBody WordVectorDTO wordVectorDTO) throws Exception {
        //�ֶμ��
                        
               //��װ��ѯ���
        return Result.success();
    }


    /**
     * ����id��ѯ
     **/
    @GetMapping("getById/{id}")
    public Result<?> getById(@PathVariable("id") Long id) {
        WordVector temp = wordVectorService.getById(id);
        //��װ��ѯ���
        return Result.success(temp);
    }

    /**
     * ����id����
     **/
    @PostMapping("deleteById/{id}")
    public Result<?> deleteById(@PathVariable("id") Long id) {
        wordVectorService.removeById(id);
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
        wordVectorService.deleteByIds(ids);
        //��װ��ѯ���
        return Result.success();
    }
    
    
}



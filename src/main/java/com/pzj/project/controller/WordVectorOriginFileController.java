package com.pzj.project.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pzj.project.common.Result;
import com.pzj.project.entity.WordVectorOriginFile;
import com.pzj.project.dto.WordVectorOriginFileDTO;
import com.pzj.project.service.WordVectorOriginFileService;
import com.pzj.project.common.enums.ErrorCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (WordVectorOriginFile)����Ʋ�
 *
 * @author makejava
 * @since 2023-02-18 20:41:33
 */
@RestController
@RequestMapping("/wordVectorOriginFile")
public class WordVectorOriginFileController {
    /**
     * �������
     */
    @Autowired
    private WordVectorOriginFileService wordVectorOriginFileService;

    /**
     * �б��ѯ����ҳ ��ѯȫ��
     **/
    @PostMapping("/listAll")
    public Result<?> listAll(@RequestBody WordVectorOriginFileDTO wordVectorOriginFileDTO) {
        //������ѯ
        QueryWrapper<WordVectorOriginFile> queryWrapper = new QueryWrapper();
        //����
        // queryWrapper.lambda().orderByDesc(WordVectorOriginFile::getCreateTime);
        //ִ�в�ѯ
        List<WordVectorOriginFile> list = wordVectorOriginFileService.list(queryWrapper);
        //��װ��ѯ���
        return Result.success(list);
    }
    
    /**
     * �б��ѯ ��ҳ
     * ֻ�ܲ�ѯ�Լ���
     **/
    @PostMapping("/listPage")
    public Result<?> listPage(@RequestBody WordVectorOriginFileDTO wordVectorOriginFileDTO) {
        
        PageHelper.startPage(wordVectorOriginFileDTO.getPageNum(),wordVectorOriginFileDTO.getPageSize());
        //������ѯ
        QueryWrapper<WordVectorOriginFile> queryWrapper = new QueryWrapper();
        
        //����
        // queryWrapper.lambda().orderByDesc(WordVectorOriginFile::getCreateTime);
        //ִ�в�ѯ
        List<WordVectorOriginFile> list = wordVectorOriginFileService.list(queryWrapper);
        PageInfo<WordVectorOriginFile> pageInfo = new PageInfo<>(list);
        //��װ��ѯ���
        return Result.success(pageInfo);
    }
    
   /**
     * ����
     **/
    @PostMapping("/insert")
    public Result<?> insert(@RequestBody WordVectorOriginFileDTO wordVectorOriginFileDTO) {

                        //��װ��ѯ���
        return Result.success();
    }

    /**
     * ����id����
     **/
    @PostMapping("updateById")
    public Result<?> updateById(@RequestBody WordVectorOriginFileDTO wordVectorOriginFileDTO) throws Exception {
        //�ֶμ��
                        
               //��װ��ѯ���
        return Result.success();
    }


    /**
     * ����id��ѯ
     **/
    @GetMapping("getById/{id}")
    public Result<?> getById(@PathVariable("id") Long id) {
        WordVectorOriginFile temp = wordVectorOriginFileService.getById(id);
        //��װ��ѯ���
        return Result.success(temp);
    }

    /**
     * ����id����
     **/
    @PostMapping("deleteById/{id}")
    public Result<?> deleteById(@PathVariable("id") Long id) {
        wordVectorOriginFileService.removeById(id);
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
        wordVectorOriginFileService.deleteByIds(ids);
        //��װ��ѯ���
        return Result.success();
    }
    
    
}



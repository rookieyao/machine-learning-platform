package com.pzj.project.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pzj.project.mapper.WordVectorMapper;
import com.pzj.project.entity.WordVector;
import com.pzj.project.service.WordVectorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * (WordVector)�����ʵ����
 *
 * @author makejava
 * @since 2023-02-18 20:34:16
 */
@Service("wordVectorService")
public class WordVectorServiceImpl extends ServiceImpl<WordVectorMapper, WordVector> implements WordVectorService {


    @Resource
    private WordVectorMapper wordVectorMapper;
    
    /**
     * ����idsɾ����������
     **/
    @Override
    public void deleteByIds(String ids) {
        String[] idsArr = ids.split(",");
        if (idsArr.length > 0) {
            List<Long> idList = Stream.of(idsArr).map(Long::valueOf).collect(Collectors.toList());
            wordVectorMapper.deleteBatchIds(idList);
        }
    }
    
    
}



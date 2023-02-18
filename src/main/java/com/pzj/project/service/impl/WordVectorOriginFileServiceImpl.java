package com.pzj.project.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pzj.project.mapper.WordVectorOriginFileMapper;
import com.pzj.project.entity.WordVectorOriginFile;
import com.pzj.project.service.WordVectorOriginFileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * (WordVectorOriginFile)�����ʵ����
 *
 * @author makejava
 * @since 2023-02-18 20:41:33
 */
@Service("wordVectorOriginFileService")
public class WordVectorOriginFileServiceImpl extends ServiceImpl<WordVectorOriginFileMapper, WordVectorOriginFile> implements WordVectorOriginFileService {


    @Resource
    private WordVectorOriginFileMapper wordVectorOriginFileMapper;
    
    /**
     * ����idsɾ����������
     **/
    @Override
    public void deleteByIds(String ids) {
        String[] idsArr = ids.split(",");
        if (idsArr.length > 0) {
            List<Long> idList = Stream.of(idsArr).map(Long::valueOf).collect(Collectors.toList());
            wordVectorOriginFileMapper.deleteBatchIds(idList);
        }
    }
    
    
}



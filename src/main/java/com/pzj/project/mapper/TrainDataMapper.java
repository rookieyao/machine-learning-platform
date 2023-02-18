package com.pzj.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pzj.project.vo.TrainDataVersionVo;
import com.pzj.project.dto.TrainDataDTO;
import org.apache.ibatis.annotations.Mapper;
import com.pzj.project.entity.TrainData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (TrainData)�����ݿ���ʲ�
 *
 * @author makejava
 * @since 2023-02-15 21:52:48
 */
@Mapper
public interface TrainDataMapper extends BaseMapper<TrainData> {

    List<TrainDataVersionVo> listPage(@Param("userId") String userId, @Param("trainDataDTO") TrainDataDTO trainDataDTO);
}



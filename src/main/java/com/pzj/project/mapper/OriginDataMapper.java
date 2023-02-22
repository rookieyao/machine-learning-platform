package com.pzj.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pzj.project.dto.OriginDataDTO;
import com.pzj.project.model.OriginDataModel;
import org.apache.ibatis.annotations.*;
import com.pzj.project.entity.OriginData;

import java.util.ArrayList;

/**
 * (OriginData)�����ݿ���ʲ�
 *
 * @author makejava
 * @since 2023-02-18 12:11:12
 */
@Mapper
public interface OriginDataMapper extends BaseMapper<OriginData> {

    /**
     * 查找原始数据名称
     *
     * @return
     */
    @Select({"<script>" +
            "select * from origin_data where process_state=#{processState}" +
            "<if test='originDataName!=null and originDataName!=\"\" '>" +
            "and origin_data_name=#{originDataName}" +
            "</if>" +
            "limit #{pageNum},#{pageSize}" +
            "</script>"
    })
    ArrayList<OriginDataModel> getByOriginData(OriginDataDTO originDataDTO);

    @Update("UPDATE origin_data SET is_delete=1 WHERE `id` = #{id};")
    int delById(@Param("id") Long id);
}



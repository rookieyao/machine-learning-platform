package com.pzj.project.mapper;

import com.pzj.project.dto.OriginDataDTO;
import com.pzj.project.model.OriginAppendDataModel;
import com.pzj.project.model.OriginDataModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Map;

@Repository
@Mapper
public interface OriginDataMapper {
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
}

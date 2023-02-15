package com.pzj.project.mapper;

import com.pzj.project.model.OriginDataModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@Mapper
public interface OriginDataMapper {
    /**
     * 查找原始数据名称
     *
     * @return
     */
    @Select({"<script>" +
            "select * from origin_data where precess_state=#{precessState}" +
            "<if test='origin_data_name!=null and origin_data_name!=\"\" '>" +
            "and origin_data_name=#{originDataName}" +
            "</if>" +
            "</script>"
    })
    ArrayList<OriginDataModel> getByOriginData(@Param("originDataName")String originDataName,
                                               @Param("precessState")Byte precessState);

}

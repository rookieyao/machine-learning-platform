package com.pzj.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pzj.project.dto.OriginDataDTO;
import com.pzj.project.entity.OriginData;
import com.pzj.project.model.OriginDataModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * (OriginData)�����ӿ�
 *
 * @author makejava
 * @since 2023-02-18 12:11:12
 */
@Service
public interface OriginDataService extends IService<OriginData> {

    /**
     * ����idsɾ����������
     **/
    void deleteByIds(String ids);

    ArrayList<OriginDataModel> getByOriginData(OriginDataDTO originDataDTO);

//    int delById(Long id);

//    int insertData(OriginData originData, MultipartFile[] multipartFiles);
    
}



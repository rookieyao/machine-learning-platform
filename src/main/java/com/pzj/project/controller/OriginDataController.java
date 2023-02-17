package com.pzj.project.controller;

import com.pzj.project.common.ResponseResult;
import com.pzj.project.dto.OriginDataDTO;
import com.pzj.project.service.OriginDataService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author rookie
 * @Date 2023-02-08 20:51
 * @Description
 **/
@CrossOrigin
@RestController
@RequestMapping("/originData")
public class OriginDataController extends ResponseResult {
    @Resource
    private OriginDataService originDataService;

    @GetMapping("/listPage")
    public Map<String,Object> getByOriginDataName(@RequestBody OriginDataDTO originDataDTO){
        // 只返回了第xx页的多少条数据
        return setResultOk(originDataService.getByOriginData(originDataDTO));
    }

}

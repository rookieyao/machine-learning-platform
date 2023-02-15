package com.pzj.project.controller;

import com.pzj.project.common.ResponseResult;
import com.pzj.project.service.OriginDataService;
import com.pzj.project.service.impl.OriginDataServiceImpl;
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

    @GetMapping("/getByOriginDataName")
    public Map<String,Object> getByOriginDataName(@RequestParam(name = "originDataName",required = false) String originDataName,
                                                  @RequestParam(name = "precessState",required = false) Byte precessState,
                                                  @RequestParam("pageNum") int pageNum,
                                                  @RequestParam("lineNum") int lineNum){
        return setResultOk(originDataService.getByOriginData(originDataName,precessState,pageNum,lineNum));
    }

}

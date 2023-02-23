package com.pzj.project.controller;

import com.pzj.project.common.minio.MinIOService;
import com.pzj.project.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Author rookie
 * @Date 2023-02-08 20:44
 * @Description
 **/

@CrossOrigin
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    TestService testService;

    @Resource
    MinIOService minIOService;

    @PostMapping("/hello")
    public String sayHello(@RequestParam("file") MultipartFile multipartFile) throws Exception{

        minIOService.uploadMultipartFile("/test/test.txt", multipartFile);

        return "ok";
    }
}

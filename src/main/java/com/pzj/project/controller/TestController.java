package com.pzj.project.controller;

import com.pzj.project.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author rookie
 * @Date 2023-02-08 20:44
 * @Description
 **/

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    TestService testService;

    @GetMapping("/hello")
    public String sayHello(){

        return testService.say();
    }
}

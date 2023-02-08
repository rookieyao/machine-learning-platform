package com.pzj.project.service.impl;

import com.pzj.project.mapper.TestMapper;
import com.pzj.project.service.TestService;

import javax.annotation.Resource;

/**
 * @Author rookie
 * @Date 2023-02-08 20:47
 * @Description
 **/
public class TestServiceImpl implements TestService {

    @Resource
    TestMapper testMapper;

    @Override
    public String say() {
        return "";
    }
}

package com.pzj.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(value = "com.pzj.project.mapper")
public class MachineLearningPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(MachineLearningPlatformApplication.class, args);
    }

}

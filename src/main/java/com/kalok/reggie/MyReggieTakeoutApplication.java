package com.kalok.reggie;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@MapperScan(basePackages = {"com.kalok.reggie.mapper"}) //扫描mapper包下的mapper接口
public class MyReggieTakeoutApplication {

    public static void main(String[] args) {
        log.info("my-reggie-takeout 正在启动");
        SpringApplication.run(MyReggieTakeoutApplication.class, args);
    }

}

package com.kalok.reggie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class MyReggieTakeoutApplication {

    public static void main(String[] args) {
        log.info("my-reggie-takeout 正在启动");
        SpringApplication.run(MyReggieTakeoutApplication.class, args);
    }

}

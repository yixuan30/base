package com.pactera.sys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.pactera"})
@MapperScan("com.pactera.sys.mapper")
public class sysApplication {
        public static void main(String[] args) {
            SpringApplication.run(sysApplication.class,args);
        }
    }

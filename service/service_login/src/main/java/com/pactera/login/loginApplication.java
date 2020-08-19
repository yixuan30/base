package com.pactera.login;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.pactera"})
@MapperScan("com.pactera.login.mapper")
public class loginApplication {
    public static void main(String[] args) {
        SpringApplication.run(loginApplication.class,args);
    }
}

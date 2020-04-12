package com.example.pagehelper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.example.pagehelper.**.mapper")
public class PagehelperdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PagehelperdemoApplication.class, args);
    }

}

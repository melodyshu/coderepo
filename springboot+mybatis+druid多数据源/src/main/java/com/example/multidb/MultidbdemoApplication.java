package com.example.multidb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("com.example.multidb.mapper")
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
public class MultidbdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultidbdemoApplication.class, args);
    }

}

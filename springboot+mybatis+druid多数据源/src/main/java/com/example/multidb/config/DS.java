package com.example.multidb.config;

import java.lang.annotation.*;

/**
 * @Author: huangzhimao
 * @Date: 2020-04-12
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface DS {
    String value() default "readTestDb";
}

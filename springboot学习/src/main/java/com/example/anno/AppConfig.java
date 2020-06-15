package com.example.anno;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author: huangzhimao
 * @Date: 2020-06-15
 * @Description:
 */
@Configuration
@Import({TestBean1.class,TestImportSelector.class,TestImportBeanDefinitionRegistrar.class})
public class AppConfig {

}

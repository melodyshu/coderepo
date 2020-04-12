package com.example.multidb.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.Driver;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: huangzhimao
 * @Date: 2020-04-12
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "readTestDb")
    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
    public DataSource readTestDb() {
        return new DruidDataSource();
    }



    @Bean(name = "writeTestDb")
    @ConfigurationProperties(prefix = "spring.datasource.druid.slave-alpha")
    public DataSource writeTestDb() {
        return new DruidDataSource();
    }


    /**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     *
     * @return
     */
    @Primary
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(readTestDb());
        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap<Object, Object>();
        dsMap.put("readTestDb", readTestDb());
        dsMap.put("writeTestDb", writeTestDb());

        dynamicDataSource.setTargetDataSources(dsMap);
        return dynamicDataSource;
    }

    /**
     * 配置@Transactional注解事物
     *
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
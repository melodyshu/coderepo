package com.example.multidb.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Author: huangzhimao
 * @Date: 2020-04-12
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        System.out.println("数据源为"+DataSourceContextHolder.getDB());
        return DataSourceContextHolder.getDB();
    }
}

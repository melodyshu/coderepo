package com.example.pagehelper.page;

import com.github.pagehelper.Page;
import org.apache.ibatis.cache.CacheKey;

/**
 * @Author: huangzhimao
 * @Date: 2020-05-06
 */
public class HivePageHelper {
    public String getCountSql(String sql){
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT COUNT(1) FROM (");
        sqlBuilder.append(sql);
        sqlBuilder.append(") TMP_TAB");
        return sqlBuilder.toString();
    }

    public String getPageSql(String sql, Page page) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(sql);
        int pageNum = page.getPageNum();
        int pageSize = page.getPageSize();
        int pageStart = (pageNum - 1) * pageSize;
        sqlBuilder.append(" LIMIT "+pageStart+","+pageSize);
        return sqlBuilder.toString();
    }
}

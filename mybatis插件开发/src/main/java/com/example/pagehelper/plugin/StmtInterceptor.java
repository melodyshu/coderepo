package com.example.pagehelper.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandler;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

/**
 * @Author: huangzhimao
 * @Date: 2020-05-07
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
@Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
@Signature(type = StatementHandler.class, method = "batch", args = { Statement.class })})
public class StmtInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        Statement stmt = (Statement) args[0];
        int count = stmt.executeUpdate("insert into mytest values(1,'aaa')");
        System.out.println("更新行数:"+count);
        try {
            return invocation.proceed();
        } finally {

            //Object parameterObject = boundSql.getParameterObject();
            //List<ParameterMapping> parameterMappingList = boundSql.getParameterMappings();
            //System.out.println("SQL：[" + sql + "]执行耗时[" + sqlCost + "ms]");
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}

package org.example;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.*;
import com.alibaba.druid.sql.dialect.hive.parser.HiveStatementParser;
import com.alibaba.druid.sql.dialect.hive.visitor.HiveOutputVisitor;
import com.alibaba.druid.util.JdbcConstants;

import java.util.List;

/**
 * @Author: huangzhimao
 * @Date: 2020-04-14
 */

/**
 * <dependency>
 *    <groupId>com.alibaba</groupId>
 *    <artifactId>druid</artifactId>
 *    <version>1.1.22</version>
 * </dependency>
 */
public class HiveSQLParse {
    public static void main(String[] args) {
        parse();
        getType();
    }

    /**
     * 利用druid将一条混合SQL(逗号分隔)提取出来
     */
    public static void parse() {
        //String sql = "select * from mytest1 where id=';    aa;';   select * from mytest2 where name='aa;a'";
        String sql = "select * from mytest1 where id=';    aa;';   insert into table mytest values(100,'a;aa')";
        List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, JdbcConstants.HIVE);
        for (SQLStatement sqlStatement : stmtList) {
            //关键字小写
            String s = sqlStatement.toLowerCaseString();
            System.out.println(s);
            System.out.println("=======");
        }
    }

    /**
     * 利用druid解析SQL语句类型,例如select,update,delete,insert,alter等
     */
    public static void getType() {
        String sql = "insert into table mytest values(100,'a;aa')";
        HiveStatementParser parser = new HiveStatementParser(sql);
        SQLStatement stmt = parser.parseStatement();
        if (stmt instanceof SQLSelectStatement) {
            System.out.println("select");
        } else if (stmt instanceof SQLInsertStatement) {
            System.out.println("insert");
        } else if (stmt instanceof SQLUpdateStatement) {
            System.out.println("update");
        } else if (stmt instanceof SQLDeleteStatement) {
            System.out.println("delete");
        } else if (stmt instanceof SQLAlterTableStatement) {
            System.out.println("alter");
        }
    }
}

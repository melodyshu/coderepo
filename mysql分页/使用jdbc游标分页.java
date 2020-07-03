package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        select(1,10);
    }

    public static void select(int pageNo,int pageSize){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url="jdbc:mysql://10.101.236.3:3306/study?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=GMT-8";
        String username="root";
        String password="MySQL2.mid";
        Connection conn  = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            String sql = "select id,age,name from test order by id";
            //  conn.prepareStatement(sql,游标类型,能否更新记录);
            //   游标类型：
            //    ResultSet.TYPE_FORWORD_ONLY:只进游标
            //    ResultSet.TYPE_SCROLL_INSENSITIVE:可滚动。但是不受其他用户对数据库更改的影响。
            //    ResultSet.TYPE_SCROLL_SENSITIVE:可滚动。当其他用户更改数据库时这个记录也会改变。
            //   能否更新记录：
            //    ResultSet.CONCUR_READ_ONLY,只读
            //    ResultSet.CONCUR_UPDATABLE,可更新
            PreparedStatement stmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            int rows=(pageNo-1)*pageSize;
            //最大查询到第几条记录
            stmt.setMaxRows(rows+pageSize);
            ResultSet resultSet = stmt.executeQuery();
            //将游标移动到第一条记录
            resultSet.first();
            //游标移动到要输出的第一条记录
            resultSet.relative(rows-1);
            while (resultSet.next()) {
                int id=resultSet.getInt("id");
                int age=resultSet.getInt("age");
                String name=resultSet.getString("name");
                System.out.println(id+","+age+","+name);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

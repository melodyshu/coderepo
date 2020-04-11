package com.example.jdbctemplate.jdbc.controller;

import com.example.jdbctemplate.jdbc.domain.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.*;

/**
 * @Author: huangzhimao
 * @Date: 2020-04-10
 */
@RestController
@RequestMapping("/jdbc")
public class JdbcController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 使用jdbcTemplate写法
     * @return
     */
    @GetMapping("/list")
    public ResponseData list(){
        String sql="select * from sys_dept";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        ResponseData responseData=new ResponseData();
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(list);
        return responseData;
    }

    /**
     * 使用原始JDBC,最普通写法
     * @return
     * @throws Exception
     */
    @GetMapping("/list2")
    public ResponseData list2() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/ry?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false";
        String username="root";
        String password="123456";
        Connection conn = DriverManager.getConnection(url, username, password);
        Statement stmt = conn.createStatement();
        //String sql="select dept_id as id,dept_name as name from sys_dept";
        String sql="select * from tbs";
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        String columnName1 = metaData.getColumnName(1);//非as
        String columnLabel = metaData.getColumnLabel(1);//as,列别名
        List<Map<String,Object>> list=new ArrayList<>();
        while (rs.next()){
            Map<String,Object> map=new HashMap<>();
            for (int i = 0; i < columnCount; i++) {
                String columnName = metaData.getColumnLabel(i+1);
                Object value = rs.getObject(i+1);
                map.put(columnName,value);
            }
            list.add(map);
        }

        ResponseData responseData=new ResponseData();
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(list);

        return responseData;
    }


    /**
     * 仿jdbcTemplate的方法
     * @return
     */
    @GetMapping("/list3")
    public ResponseData list3() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/ry?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false";
        String username="root";
        String password="123456";
        Connection conn = DriverManager.getConnection(url, username, password);
        Statement stmt = conn.createStatement();
        String sql="select dept_id,dept_name,create_time from sys_dept";
        ResultSet resultSet = stmt.executeQuery(sql);

        ColumnMapRowMapper columnMapRowMapper = new ColumnMapRowMapper();
        RowMapperResultSetExtractor<Map<String,Object>> rowMapperResultSetExtractor = new RowMapperResultSetExtractor<>(columnMapRowMapper);
        List<Map<String, Object>> list = rowMapperResultSetExtractor.extractData(resultSet);

        ResponseData responseData=new ResponseData();
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(list);
        return responseData;
    }

    /**
     * 从spring源码抠
     * @return
     * @throws Exception
     */
    @GetMapping("/list4")
    public ResponseData list4() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/ry?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false";
        String username="root";
        String password="123456";
        Connection conn = DriverManager.getConnection(url, username, password);
        Statement stmt = conn.createStatement();
        String sql="select dept_id,dept_name,create_time from sys_dept";
        ResultSet resultSet = stmt.executeQuery(sql);
        List<Map<String, Object>> list=new ArrayList<>();

        while (resultSet.next()) {
            Map<String, Object> map = mapRow(resultSet);
            list.add(map);
        }

        ResponseData responseData=new ResponseData();
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(list);
        return responseData;

    }


    private Map<String, Object> mapRow(ResultSet rs) throws SQLException {
        ResultSetMetaData resultSetMetaData = rs.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        Map<String, Object> map = new LinkedHashMap<>(columnCount);

        for (int i = 1; i <= columnCount; i++) {
            String column = resultSetMetaData.getColumnLabel(i);
            Object value = rs.getObject(i);
            map.putIfAbsent(column, value);
        }

        return map;
    }
}

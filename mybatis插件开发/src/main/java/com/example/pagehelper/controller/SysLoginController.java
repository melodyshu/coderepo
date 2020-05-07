package com.example.pagehelper.controller;

import com.example.pagehelper.domain.SqlBean;
import com.example.pagehelper.domain.SysLogininfor;
import com.example.pagehelper.mapper.SysLogininforMapper;
import com.example.pagehelper.page.PageDomain;
import com.example.pagehelper.page.TableDataInfo;
import com.example.pagehelper.page.TableSupport;
import com.example.pagehelper.util.SqlUtil;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

/**
 * @Author: huangzhimao
 * @Date: 2020-04-11
 */
@RestController
@ResponseBody
@RequestMapping("/login")
public class SysLoginController extends BaseController{

    @Autowired
    private SysLogininforMapper logininforMapper;

    @GetMapping("/list")
    public TableDataInfo list(SysLogininfor logininfor)
    {
        startPage();

        List<SysLogininfor> list = logininforMapper.selectLogininforList(logininfor);
        TableDataInfo dataTable = getDataTable(list);
        return dataTable;
    }

    @PostMapping("/sql")
    @ResponseBody
    public TableDataInfo sqlList(@RequestBody SqlBean sqlBean)
    {
        //startPage();
        String sqlstr = sqlBean.getSqlstr();
        List<Map<String, Object>>  list= logininforMapper.selectList(sqlstr);
        TableDataInfo dataTable = getDataTable(list);
        return dataTable;
    }




}

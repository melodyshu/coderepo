package com.example.pagehelper.controller;

import com.example.pagehelper.domain.SysLogininfor;
import com.example.pagehelper.mapper.SysLogininforMapper;
import com.example.pagehelper.page.PageDomain;
import com.example.pagehelper.page.TableDataInfo;
import com.example.pagehelper.page.TableSupport;
import com.example.pagehelper.util.SqlUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @ResponseBody
    public TableDataInfo list(SysLogininfor logininfor)
    {
        startPage();
        List<SysLogininfor> list = logininforMapper.selectLogininforList(logininfor);
        TableDataInfo dataTable = getDataTable(list);
        return dataTable;
    }




}

package com.example.pagehelper.controller;

import com.example.pagehelper.domain.SysLogininfor;
import com.example.pagehelper.mapper.SysLogininforMapper;
import com.example.pagehelper.page.PageDomain;
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
public class SysLoginController {

    @Autowired
    private SysLogininforMapper logininforMapper;

    @GetMapping("/list")
    @ResponseBody
    public List<SysLogininfor> list(SysLogininfor logininfor)
    {
        startPage();
        List<SysLogininfor> list = logininforMapper.selectLogininforList(logininfor);
        return list;
    }

    /**
     * 设置请求分页数据
     */
    private void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if ((pageNum != null) && (pageSize != null))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }


}

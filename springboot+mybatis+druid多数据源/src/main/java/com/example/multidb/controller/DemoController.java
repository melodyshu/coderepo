package com.example.multidb.controller;

import com.example.multidb.model.User;
import com.example.multidb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: huangzhimao
 * @Date: 2020-04-12
 */
@RestController
@RequestMapping
@ResponseBody
public class DemoController {
    @Autowired
    private UserService userService;

    @GetMapping("/get1")
    public User getRequestDBObj() {
        return userService.selectByPrimaryKey(1);
    }

    @GetMapping("/get2")
    public User getRequestDbList() {
        return userService.selectAllUser(1);
    }
}

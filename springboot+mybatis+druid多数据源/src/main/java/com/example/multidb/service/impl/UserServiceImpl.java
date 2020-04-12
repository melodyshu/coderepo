package com.example.multidb.service.impl;

import com.example.multidb.config.DS;
import com.example.multidb.mapper.UserMapper;
import com.example.multidb.model.User;
import com.example.multidb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: huangzhimao
 * @Date: 2020-04-12
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    @DS("writeTestDb")
    public User selectByPrimaryKey(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    @DS("readTestDb")
    public User selectAllUser(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}

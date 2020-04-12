package com.example.multidb.service;

import com.example.multidb.model.User;

/**
 * @Author: huangzhimao
 * @Date: 2020-04-12
 */
public interface UserService {
    public User selectByPrimaryKey(Integer userId);

    public User selectAllUser(Integer userId);
}

package com.example.multidb.mapper;

import com.example.multidb.model.User;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: huangzhimao
 * @Date: 2020-04-12
 */
public interface UserMapper {
    @Select("SELECT user_id as userId, user_name as userName, password, phone FROM t_user WHERE user_id = #{userId}")
    User selectByPrimaryKey(Integer userId);
}

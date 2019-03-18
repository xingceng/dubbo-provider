package com.jk.dao;

import com.jk.model.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LoginDao {

    @Select("SELECT * FROM  t_user u WHERE u.`username`=#{username}")
    List<User> getTestNameIsNull(User user);
}
